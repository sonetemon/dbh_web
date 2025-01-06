package com.dbh.ilps_service.loan_approval.repository;

import com.dbh.ilps_service.loan_approval.dto.projection.LoanApprovalProjection;
import com.dbh.ilps_service.loan_approval.entity.LoanRequestPending;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomLoanApprovalRepositoryImpl implements CustomLoanApprovalRepository {

    private final EntityManager entityManager;

    private final UserBranchRepository userBranchRepository;

    @Override
    public Page<LoanApprovalProjection> searchLoanApprovalPending(String userName, String desig, String fileStage, String fileNo, String customerName, Pageable pageable) {

        // 1️⃣ Execute pre-block logic (modify or validate input parameters)
        preBlockLogic(userName, desig, fileStage);

        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();

        // 2️⃣ SQL for paginated data
        String sql = "SELECT FILE_NO as fileNo, NAME AS customerName FROM ( " +
                "    SELECT e.*, ROW_NUMBER() OVER (ORDER BY e.FILE_NO) rn " +
                "    FROM ILPS.LOAN_REG_PEND e " +
                "    WHERE " + getWhereClause(userName, desig, fileStage, fileNo, customerName) +
                ") WHERE rn > :offset AND rn <= :endRow";

        // 3️⃣ SQL for total count
        String countSql = "SELECT COUNT(*) FROM ILPS.LOAN_REG_PEND e WHERE " +
                getWhereClause(userName, desig, fileStage, fileNo, customerName);

        // 4️⃣ Query for paginated data
        String fileStageString = fileStage != null ? String.valueOf(fileStage) : null;
        Query dataQuery = entityManager.createNativeQuery(sql, LoanApprovalProjection.class);
        setParameters(dataQuery,  fileNo, customerName);
        dataQuery.setParameter("offset", offset);
        dataQuery.setParameter("endRow", offset + limit);
        List<LoanApprovalProjection> loanRequestPendingList = dataQuery.getResultList();
/*        List<Object[]> resultList = dataQuery.getResultList();
        List<LoanApprovalProjection> loanRequestPendingList = resultList.stream()
                .map(obj -> new LoanApprovalProjection(
                        ((Number) obj[0]).longValue(),  // fileNo
                        (String) obj[1]  // customerName
                ))
                .collect(Collectors.toList());*/



        // 5️⃣ Query for total count
        Query countQuery = entityManager.createNativeQuery(countSql);
        setParameters(countQuery,  fileNo, customerName);
        Number totalCount = ((Number) countQuery.getSingleResult());

        return new PageImpl<>(loanRequestPendingList, pageable, totalCount.longValue());
    }

    /**
     * Pre-block logic to process or modify input parameters before executing the SQL queries.
     * This can include trimming strings, converting fileStage to string, and other pre-processing logic.
     *
     * @return
     */
    private String preBlockLogic(String userName, String desig, String fileStage) {
        // Trim input strings to avoid whitespace issues
            String cond1;
            String cond2 = null;
            String cond3 = null;
            String file_sts1;
            String file_sts2;
            StringBuilder globalCond3 = new StringBuilder();
        List<Integer> branchCodes;

            // Step 2: Build condition for COND3 based on user designation and stage
            if (!isHighLevelUser(desig)) {
                if (!"3".equals(fileStage)) {
                    branchCodes = userBranchRepository.findBranchCodesByUsername(userName);
                    cond3 = buildConditionString(branchCodes, "dbh_user.ret_brn(file_no)");
                } else {
                    branchCodes = userBranchRepository.findBranchCodesWithUnion(userName);
                    cond3 = buildConditionString(branchCodes, "nvl(proc_brn1, dbh_user.ret_brn(file_no))");
                }
                //return cond3;

               // return fileStageCondition(fileStage) + (cond3 != null ? " AND " + cond3 : "") + " AND nvl(hub, 0) <> 1";

            }

            // Step 3: Handle Stage-wise logic
            switch (fileStage) {
                case "1":
                    cond2 = setConditionForAppraiserModule(cond3, userName);
                    break;
                case "2":
                    cond2 = setConditionForFirstDisbursementModule(cond3, userName);
                    break;
                case "3":
                    cond2 = setConditionForDoubleCheckingModule(cond3);
                    break;
                case "4":
                    cond2 = setConditionForApprovalModule(cond3, desig, userName);
                    break;
                case "6":
                    cond2 = setConditionForRecommendationForCancellation(cond3);
                    break;
                case "7":
                    cond2 = setConditionForApprovalOfCancellation(cond3);
                    break;
                case "8":
                    cond2 = setConditionForSubsequentDisbursement(cond3, userName);
                    break;
                case "9":
                    cond2 = setConditionForSanctionAcceptanceModule(cond3);
                    break;
            }
            return cond2;
        }

        private boolean isHighLevelUser(String desig) {
            return "VPC".equals(desig) || "HOHL".equals(desig) ||
                    "HOC".equals(desig) || "MD".equals(desig);
        }

        /**
         * Build SQL condition for a list of branch codes
         */
        private String buildConditionString(List<Integer> codes, String column) {
            if (codes == null || codes.isEmpty()) {
                return null;
            }
            return column + " IN (" + codes.stream().map(String::valueOf).collect(Collectors.joining(", ")) + ")";
        }
        private String setConditionForAppraiserModule(String COND3, String userName) {
           String file_sts1 = "CR\\APPR";
            return "(FILE_STS = '" + file_sts1 + "' OR substr(file_sts, 8, 15) = '" + file_sts1 + "') " +
                    "AND appr_by = '" + userName + "' AND (" + COND3 + ") AND NVL(hub, 0) <> 1";
        }

        private String setConditionForFirstDisbursementModule(String COND3, String userName) {
            String file_sts1 = "SN\\ACPT";
            String file_sts2 = "SN\\LGOV";
            return "(FILE_STS IN ('" + file_sts1 + "', '" + file_sts2 + "')) AND (" + COND3 + ") AND NVL(mrcd, 0) = 0 AND appr_by <> '" + userName + "'";
        }

        private String setConditionForDoubleCheckingModule(String COND3) {
            String file_sts1 = "CR\\ROVR";
            return "(FILE_STS = '" + file_sts1 + "' OR substr(file_sts, 8, 15) = '" + file_sts1 + "') AND (" + COND3 + ") AND NVL(hub, 0) <> 1";
        }

        private String setConditionForApprovalModule(String COND3, String desig, String userName) {
            String file_sts1 = "CR\\DCOV";
            String file_sts2 = "CR\\PFAP";
            String innerCondition = "NVL(baa_req, 0) <> 1 AND NVL(bom_req, 0) <> 1 AND NVL(fapc_req, 0) = 1";
            String cond2;

            switch (desig) {
                case "VPC":

                case "HOHL":
                case "HOP":
                    cond2 = "(FILE_STS = '" + file_sts2 + "' OR substr(file_sts, 8, 15) = '" + file_sts2 + "') and nvl(baa_req,0)<>1 and nvl(bom_req,0)<>1 and nvl(fapc_Req,0)<>1 and nvl(hoc_req,0)<>1  and hopn_Req=1";
                    break;
                case "HOC":
                    cond2 = "(FILE_STS = '" + file_sts2 + "' OR substr(file_sts, 8, 15) = '" + file_sts2 + "') and nvl(baa_req,0)<>1 and nvl(bom_req,0)<>1 and nvl(fapc_Req,0)<>1 and nvl(hoc_req,0)<>1  and nvl(hopn_req,0)<>1 and hop_Req=1";
                    break;
                case "MD":
                    cond2 = "(FILE_STS = '" + file_sts2 + "' OR substr(file_sts, 8, 15) = '" + file_sts2 + "') and nvl(baa_req,0)<>1 and nvl(bom_req,0)<>1  and nvl(fapc_Req,0)<>1 and nvl(hoc_req,0)<>1 and nvl(hop_req,0)<>1 and nvl(hopn_req,0)<>1  and md_Req=1 AND APPROV1_BY <> '" + userName + "'";
                    break;
                default:
                    cond2 = "(FILE_STS = '" + file_sts1 + "' OR substr(file_sts, 8, 15) = '" + file_sts1 + "') OR ((FILE_STS = '" + file_sts2 + "' OR substr(file_sts, 8, 15) = '" + file_sts2 + "'))  AND (" + COND3 + ") AND NVL(hub, 0) <> 1";
                    break;
            }
            return cond2;
        }

        private String setConditionForRecommendationForCancellation(String COND3) {
            String file_sts1 = "SN\\SANC";
            String file_sts2 = "SN\\ACPT";
            return "(FILE_STS IN ('" + file_sts1 + "', '" + file_sts2 + "')) AND (tot_disb = 0 OR tot_disb IS NULL) AND (" + COND3 + ")";
        }

        private String setConditionForApprovalOfCancellation(String COND3) {
            String file_sts1 = "CN\\ROVR";
            String file_sts2 = "CR\\CLOS";
            return "(FILE_STS IN ('" + file_sts1 + "', '" + file_sts2 + "')) AND (" + COND3 + ")";
        }

        private String setConditionForSubsequentDisbursement(String COND3,String userName) {
            String file_sts1 = "PD\\DBMD";
            String file_sts2 = "PD\\DBMDSN\\ACPT";
            return "(FILE_STS IN ('" + file_sts1 + "', '" + file_sts2 + "')) AND (" + COND3 + ") AND NVL(mrcd, 0) = 0 AND appr_by <> '" + userName + "'";
        }

        private String setConditionForSanctionAcceptanceModule(String COND3) {
            String file_sts1 = "SN\\SANC";
            String file_sts2 = "CR\\PFAP";
            return "(FILE_STS IN ('" + file_sts1 + "', '" + file_sts2 + "')) AND (" + COND3 + ")";
        }


    /**
     * Builds the WHERE clause dynamically based on the provided parameters.
     */
    private String getWhereClause( String userName, String desig, String fileStage, String fileNo, String customerName) {
        List<String> conditions = new ArrayList<>();

 /*       if (userName != null && !userName.isEmpty()) {
            conditions.add("e.USER_NAME = :userName");
        }

          if (fileStage != null) {
            conditions.add("e.FILE_STAGE = :fileStage");
        }

        if (desig != null && !desig.isEmpty()) {
            conditions.add("e.DESIG = :desig");
        }  */
        if (fileNo != null && !fileNo.isEmpty()) {
            conditions.add("e.FILE_NO = :fileNo");
        }

        if (customerName != null && !customerName.isEmpty()) {
            conditions.add("e.NAME LIKE :customerName");
        }

        return conditions.isEmpty() ? preBlockLogic(userName, desig, fileStage).toString() : String.join(" AND ", conditions);
        //return conditions.isEmpty() ? "1=1" : String.join(" AND ", conditions);
    }

    /**
     * Sets parameters for the provided query object.
     */
    private void setParameters(Query query,String fileNo, String customerName) {

        if (fileNo != null && !fileNo.isEmpty()) {
            query.setParameter("fileNo", fileNo);
        }
        if (customerName != null && !customerName.isEmpty()) {
            query.setParameter("customerName", "%" + customerName + "%");
        }
    }
}
