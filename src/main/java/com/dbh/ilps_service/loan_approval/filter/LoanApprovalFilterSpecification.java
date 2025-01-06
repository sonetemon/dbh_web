package com.dbh.ilps_service.loan_approval.filter;
import jakarta.persistence.TypedQuery;
public class LoanApprovalFilterSpecification {
    public static String buildQuery(LoanApprovalFilter filter) {

        StringBuilder queryBuilder = new StringBuilder("SELECT new com.dbh.ilps_service.loan_approval.dto.projection.LoanApprovalProjection(" +
                "u.fileNo, u.customerName) " +
                "FROM LoanRequestPending u WHERE 1=1");

        if (filter.getFileNo() != null) {
            queryBuilder.append(" AND u.fileNo = :fileNo");
        }
        if (filter.getCustomerName() != null) {
            queryBuilder.append(" AND UPPER(u.customerName) LIKE :customerName");
        }

        return queryBuilder.toString();
    }

    public static void setParameters(TypedQuery<?> query, LoanApprovalFilter filter) {
        if (filter.getFileNo() != null) {
            query.setParameter("fileNo", filter.getFileNo());
        }
        if (filter.getCustomerName() != null) {
            query.setParameter("name", "%" + filter.getCustomerName() + "%");
        }

    }

}
