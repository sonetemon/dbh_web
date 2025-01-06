package com.dbh.ilps_service.loan_approval.repository;

import com.dbh.ilps_service.loan_approval.entity.LoanRequestPending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRequestPendingRepository extends JpaRepository<LoanRequestPending, Integer>, CustomLoanApprovalRepository  {

    @Query(value = """
        SELECT * FROM (
            SELECT a.*, ROWNUM rnum
            FROM (
                SELECT * 
                FROM ilps.loan_reg_pend
                WHERE 
                    (:filterColumn IS NULL OR 
                    (:operator = '=' AND LOWER(:filterColumn) LIKE LOWER(:filterValue)) OR
                    (:operator = '>' AND TO_DATE(:filterColumn, 'YYYY-MM-DD') > TO_DATE(:filterValue, 'YYYY-MM-DD')) OR
                    (:operator = '<' AND TO_DATE(:filterColumn, 'YYYY-MM-DD') < TO_DATE(:filterValue, 'YYYY-MM-DD')) OR
                    (:operator = 'BETWEEN' AND TO_DATE(:filterColumn, 'YYYY-MM-DD') BETWEEN TO_DATE(:startValue, 'YYYY-MM-DD') AND TO_DATE(:endValue, 'YYYY-MM-DD')))
                ORDER BY file_no
            ) a
            WHERE ROWNUM <= :pageEnd
        )
        WHERE rnum > :pageStart
    """, nativeQuery = true)
    List<LoanRequestPending> findWithPaginationAndComplexFiltering(
            @Param("filterColumn") String filterColumn,
            @Param("filterValue") String filterValue,
            @Param("operator") String operator,
            @Param("startValue") String startValue,
            @Param("endValue") String endValue,
            @Param("pageStart") int pageStart,
            @Param("pageEnd") int pageEnd
    );


    Optional<LoanRequestPending> findByFileNo(Integer fileNo);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END " +
            "FROM ilps.loan_reg_pend u WHERE u.file_no = :fileNo",
            nativeQuery = true)
    int existsByFileNo(@Param("fileNo") Integer fileNo);


}
