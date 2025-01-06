package com.dbh.ilps_service.loan_approval.service;

import com.dbh.ilps_service.filter.CustomSpecification;
import com.dbh.ilps_service.filter.FilterCriteria;
import com.dbh.ilps_service.loan_approval.dto.projection.LoanApprovalProjection;
import com.dbh.ilps_service.loan_approval.entity.LoanRequestPending;
import com.dbh.ilps_service.loan_approval.filter.LoanApprovalFilter;
import com.dbh.ilps_service.loan_approval.filter.LoanApprovalFilterSpecification;
import com.dbh.ilps_service.loan_approval.repository.LoanRequestPendingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanRegPendService {

    private final LoanRequestPendingRepository loanRequestPendingRepository;

    private final EntityManager em;

 /*   public Page<LoanRequestPending> getApprovalPendingFiles(List<FilterCriteria> filters, Pageable pageable) {
        CustomSpecification<LoanRequestPending> specification = new CustomSpecification<>();
        filters.forEach(specification::add);
        return loanRegPendingRepository.findAll(specification, pageable);
    }

    public List<LoanRequestPending> getPaginatedDataWithComplexFilters(int page, int pageSize, Map<String, Object> filters) {
        int pageStart = (page - 1) * pageSize;
        int pageEnd = page * pageSize;

        // Extract filter parameters
        String filterColumn = (String) filters.get("filterColumn");
        String operator = (String) filters.get("operator");
        String filterValue = (String) filters.get("filterValue");
        String startValue = (String) filters.get("startValue");
        String endValue = (String) filters.get("endValue");

        // Prepare wildcard for LIKE operator if needed
        if ("=".equals(operator)) {
            filterValue = filterValue == null || filterValue.isBlank() ? null : "%" + filterValue + "%";
        }

        return loanRequestPendingRepository.findWithPaginationAndComplexFiltering(
                filterColumn, filterValue, operator, startValue, endValue, pageStart, pageEnd
        );
    }
*/

    public List<LoanApprovalProjection> loanApprovalFilter(LoanApprovalFilter filter, int page, int size) {
        String queryStr = LoanApprovalFilterSpecification.buildQuery(filter);
        TypedQuery<LoanApprovalProjection> query = em.createQuery(queryStr, LoanApprovalProjection.class);
        LoanApprovalFilterSpecification.setParameters(query, filter);
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    public long countLoanApprovalFilter(LoanApprovalFilter filter) {
        String countQueryStr = LoanApprovalFilterSpecification.buildQuery(filter)
                .replace("SELECT new com.dbh.ilps_service.loan_approval.dto.projection.LoanApprovalProjection(u.fileNo, u.customerName)", "SELECT COUNT(u)");
        TypedQuery<Long> countQuery = em.createQuery(countQueryStr, Long.class);
        LoanApprovalFilterSpecification.setParameters(countQuery, filter);

        return countQuery.getSingleResult();
    }
    public Page<LoanApprovalProjection> searchLoanApprovalPending(String userName, String desig, String fileStage,String fileNo, String customerName,  Pageable pageable) {
        return loanRequestPendingRepository.searchLoanApprovalPending(userName, desig, fileStage, fileNo, customerName,  pageable);
    }

}
