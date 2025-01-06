package com.dbh.ilps_service.loan_approval.repository;

import com.dbh.ilps_service.loan_approval.entity.UserBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBranchRepository extends JpaRepository<UserBranch, Long> {

    @Query("SELECT u.brCode FROM UserBranch u WHERE u.sw = 'ILPS' AND u.empShortName = :username")
    List<Integer> findBranchCodesByUsername(@Param("username") String username);

    @Query(value = "(SELECT ub.brCode FROM UserBranch ub WHERE ub.sw = 'ILPS' AND ub.empsrtname = :username) " +
            "UNION " +
            "(SELECT uba.brCode FROM UserBranchAdd uba WHERE uba.sw = 'ILPS' AND uba.empsrtname = :username)",
            nativeQuery = true)
    List<Integer> findBranchCodesWithUnion(@Param("username") String username);
}
