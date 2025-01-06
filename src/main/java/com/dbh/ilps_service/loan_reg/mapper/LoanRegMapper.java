package com.dbh.ilps_service.loan_reg.mapper;

import com.dbh.ilps_service.loan_reg.dto.request.LoanRegRequest;
import com.dbh.ilps_service.loan_reg.dto.response.LoanRegResponse;
import com.dbh.ilps_service.loan_reg.entity.LoanReg;
import org.springframework.stereotype.Component;

@Component
public class LoanRegMapper {

    public LoanRegResponse from(LoanReg loanReg) {   // manual bean conversion
        LoanRegResponse response = new LoanRegResponse();
        response.setFileNo(loanReg.getFileNo());
        response.setCustName(loanReg.getCustName());
        return response;
    }

    public void update(LoanReg loanReg, LoanRegRequest dto) {

        loanReg.setFileNo(dto.getFileNo());
        loanReg.setCustName(dto.getCustName());

    }

    public LoanReg save(LoanRegRequest loanRegRequest) {
        LoanReg loanReg = new LoanReg();
        loanReg.setFileNo(loanRegRequest.getFileNo());
        loanReg.setCustName(loanRegRequest.getCustName());
        return loanReg;
    }
}
