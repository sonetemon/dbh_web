package com.dbh.ilps_service.loan_approval.repository;

import com.dbh.ilps_service.loan_approval.entity.LoanRequestPending;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanApprovalEntityManager {

    @PersistenceContext
    private EntityManager em;

    public List<LoanRequestPending> findAll(int offset, int recordPerPage) {
        String sql = "SELECT e FROM LoanRequestPending e ORDER BY e.fileNo ASC";  //JPQL
        TypedQuery<LoanRequestPending> emPQuery = em.createQuery(sql, LoanRequestPending.class);
        emPQuery.setFirstResult(offset);
        emPQuery.setMaxResults(recordPerPage);
        return emPQuery.getResultList();
    }
}
