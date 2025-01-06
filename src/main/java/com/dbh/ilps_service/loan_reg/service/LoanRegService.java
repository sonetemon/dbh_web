package com.dbh.ilps_service.loan_reg.service;

import com.dbh.ilps_service.loan_reg.entity.LoanReg;
import com.dbh.ilps_service.loan_reg.exception.ResourceNotFoundException;
import com.dbh.ilps_service.loan_reg.repository.LoanRegRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanRegService {

    private final LoanRegRepository loanRegRepository;

    @Transactional
    public LoanReg save(LoanReg loanReg) {
        return loanRegRepository.save(loanReg);
    }

    @Transactional(readOnly = true)
    public List<LoanReg> findAll() {
        return loanRegRepository.findAll();
    }

    @Transactional(readOnly = true)
    public LoanReg findByLoanRegId(Integer id) {
        return loanRegRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public LoanReg findByFileNo(Integer fileNo) {
        return loanRegRepository.findByFileNo(fileNo).orElseThrow(ResourceNotFoundException::new);

    }
    public boolean isUFileNoTaken(Integer fileNo) {
        return loanRegRepository.existsByFileNo(fileNo) > 0;
    }
    @Transactional
    public void delete(Integer id) {
        LoanReg loanReg = loanRegRepository.findById(id).orElseThrow(null);
        loanRegRepository.delete(loanReg);
    }
}