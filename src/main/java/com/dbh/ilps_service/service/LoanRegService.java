package com.dbh.ilps_service.service;

import com.dbh.ilps_service.entity.LoanReg;
import com.dbh.ilps_service.exception.ResourceNotFoundException;
import com.dbh.ilps_service.repository.LoanRegRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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