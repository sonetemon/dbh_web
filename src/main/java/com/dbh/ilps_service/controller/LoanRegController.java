package com.dbh.ilps_service.controller;

import com.dbh.ilps_service.entity.LoanReg;
import com.dbh.ilps_service.service.LoanRegService;
import com.dbh.ilps_service.dto.request.LoanRegRequest;
import com.dbh.ilps_service.dto.response.LoanRegResponse;
import com.dbh.ilps_service.mapper.LoanRegMapper;
import com.dbh.ilps_service.validation.LoanRegValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import static com.dbh.ilps_service.exception.ApiError.fieldError;
import static com.dbh.ilps_service.utils.ResponseBuilder.error;
import static com.dbh.ilps_service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "ILPS API")
@RequestMapping(path = "ilps-service")
public class LoanRegController {

    private final LoanRegService loanRegService;

    private final LoanRegMapper loanRegMapper;
    private final LoanRegValidator loanRegValidator;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<LoanReg> loanRegs = loanRegService.findAll();
        return new ResponseEntity<>(loanRegs, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(summary = "find an employee by id")
    public ResponseEntity<LoanReg> findById(@PathVariable Integer id) {
        LoanReg employee = loanRegService.findByLoanRegId(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "save an employee")
    public ResponseEntity<JSONObject> save(@Valid @RequestBody LoanRegRequest loanRegRequest, BindingResult bindingResult ) {
        ValidationUtils.invokeValidator(loanRegValidator, loanRegRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }
        LoanReg loanReg = loanRegMapper.save(loanRegRequest);
        loanRegService.save(loanReg);
        LoanRegResponse loanRegResponse = loanRegMapper.from(loanReg);
        return new ResponseEntity<>(success(loanRegResponse).getJson(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LoanReg> update(@RequestBody LoanReg loanReg) {
        LoanReg loanRegResponse = loanRegService.save(loanReg);
        return new ResponseEntity<>(loanRegResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete employee by id")
    public void delete(@PathVariable Integer id) {
        loanRegService.delete(id);
        log.info("delete employee by id {}", id);
    }
}