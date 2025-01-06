package com.dbh.ilps_service.loan_approval.repository;
import com.dbh.ilps_service.loan_approval.dto.projection.LoanApprovalProjection;
import com.dbh.ilps_service.loan_approval.entity.LoanRequestPending;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface CustomLoanApprovalRepository {
    Page<LoanApprovalProjection> searchLoanApprovalPending(String userName, String desig, String fileStage, String fileNo, String customerName, Pageable pageable);

}
