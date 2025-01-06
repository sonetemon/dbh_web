package com.dbh.ilps_service.loan_reg.repository;
import com.dbh.ilps_service.loan_reg.entity.LoanReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRegRepository extends JpaRepository<LoanReg, Integer> {
    Optional<LoanReg> findByFileNo(Integer fileNo);
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END " +
            "FROM sonet.loan_reg u WHERE u.file_no = :fileNo",
            nativeQuery = true)
    int existsByFileNo(@Param("fileNo") Integer fileNo);


}
