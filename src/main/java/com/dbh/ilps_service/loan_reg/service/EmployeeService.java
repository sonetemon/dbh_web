package com.dbh.ilps_service.loan_reg.service;

import com.dbh.ilps_service.loan_reg.entity.Employee;
import com.dbh.ilps_service.loan_reg.repository.EmployeeRepository;
import com.dbh.ilps_service.loan_reg.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee findByEmployeeId(Long id) {
        return employeeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);

    }

    @Transactional
    public void delete(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(null);
        employeeRepository.delete(employee);
    }
}