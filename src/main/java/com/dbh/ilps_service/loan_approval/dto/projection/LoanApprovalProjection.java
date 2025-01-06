package com.dbh.ilps_service.loan_approval.dto.projection;

import lombok.*;

import java.util.Date;

@Data
@Builder
public class LoanApprovalProjection {

    // private Long id;
    private Long fileNo;

    private String customerName;


    public LoanApprovalProjection(Long fileNo, String customerName) {
        this.fileNo = fileNo;
        this.customerName = customerName;

    }

}
