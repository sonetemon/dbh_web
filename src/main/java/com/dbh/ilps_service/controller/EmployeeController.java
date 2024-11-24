package com.dbh.ilps_service.controller;

import com.dbh.ilps_service.entity.Employee;
import com.dbh.ilps_service.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "ILPS API")
@RequestMapping(path = "employee-service")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "find an employee by id")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        Employee employee = employeeService.findByEmployeeId(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "save an employee")
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        Employee employeeResponse = employeeService.save(employee);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        Employee employeeResponse = employeeService.save(employee);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete employee by id")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
        log.info("delete employee by id {}", id);
    }
}