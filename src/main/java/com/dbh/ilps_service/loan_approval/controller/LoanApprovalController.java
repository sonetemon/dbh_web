package com.dbh.ilps_service.loan_approval.controller;
import com.dbh.ilps_service.filter.FilterCriteria;
import com.dbh.ilps_service.loan_approval.dto.projection.LoanApprovalProjection;
import com.dbh.ilps_service.loan_approval.entity.LoanRequestPending;
import com.dbh.ilps_service.loan_approval.filter.LoanApprovalFilter;
import com.dbh.ilps_service.loan_approval.service.LoanRegPendService;
import com.dbh.ilps_service.loan_reg.utils.PaginatedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import static com.dbh.ilps_service.loan_reg.utils.ResponseBuilder.error;
import static com.dbh.ilps_service.loan_reg.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Loan approval API")
@RequestMapping(path = "loan-approval")
public class LoanApprovalController {


    @GetMapping
    public String home(){
        return "Hello World!";
    }
    private final LoanRegPendService loanRegPendService;
/*    @PostMapping("/search")
    public ResponseEntity<JSONObject> searchEntities(
            @RequestBody List<FilterCriteria> filters,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        // Prepare sorting and paging parameters
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Sort sortObj = Sort.by(direction, sort[0]);
        Pageable pageable = PageRequest.of(page, size, sortObj);

        // Invoke the service
        Page<LoanRequestPending>  response =  loanRegPendService.getApprovalPendingFiles(filters, pageable);

        return new ResponseEntity<>(success(response).getJson(), HttpStatus.CREATED);
    }

    @GetMapping("/paginated")
    public ResponseEntity<JSONObject> getPaginatedLoanRequests(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestBody Map<String, Object> filters
    ) {
        List<LoanRequestPending>  response =  loanRegPendService.getPaginatedDataWithComplexFilters(page, pageSize, filters);

        return new ResponseEntity<>(success(response).getJson(), HttpStatus.CREATED);
        //return loanRegPendService.getPaginatedDataWithFiltering(page, pageSize, filterColumn, filterValue);
    }*/


    @GetMapping("/paginated")
    public ResponseEntity<JSONObject> paginated(@RequestBody LoanApprovalFilter filter,
                                                   @RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "10") int recordsPerPage) {
        List<LoanApprovalProjection> employees = loanRegPendService.loanApprovalFilter(filter, page, recordsPerPage);
        long totalItems = loanRegPendService.countLoanApprovalFilter(filter);
        int totalPages = (int) Math.ceil((double) totalItems / recordsPerPage);
        log.info("employees: {}", employees);
        PaginatedResponse<LoanApprovalProjection> paginatedResponse = new PaginatedResponse<>(employees, page, recordsPerPage, totalItems, totalPages);
        //return new ResponseEntity<>(paginatedResponse, HttpStatus.OK);
        return new ResponseEntity<>(success(paginatedResponse).getJson(), HttpStatus.CREATED);
    }

    @GetMapping("/dd")
    public Page<LoanApprovalProjection> viewApprovalPending(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String desig,
            @RequestParam(required = false) String fileStage,
            @RequestParam(required = false) String fileNo,
            @RequestParam(required = false) String customerName,
            Pageable pageable
    ) {
        return loanRegPendService.searchLoanApprovalPending(userName, desig, fileStage, fileNo, customerName, pageable);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<LoanApprovalProjection>> searchLoanApprovalPending(
            @RequestBody Map<String, Object> requestParams,
            Pageable pageable) {

        // Convert to String properly using String.valueOf()
        //Integer fileStage = (Integer) requestParams.get("fileStage");
        String fileStage = requestParams.get("fileStage") != null ? String.valueOf(requestParams.get("fileStage")) : null;

        String desig = requestParams.get("desig") != null ? String.valueOf(requestParams.get("desig")) : null;
        String userName = requestParams.get("userName") != null ? String.valueOf(requestParams.get("userName")) : null;

        String fileNo = requestParams.get("fileNo") != null ? String.valueOf(requestParams.get("fileNo")) : null;
        String customerName = requestParams.get("customerName") != null ? String.valueOf(requestParams.get("customerName")) : null;


        Page<LoanApprovalProjection> result = loanRegPendService.searchLoanApprovalPending(userName, desig, fileStage, fileNo, customerName, pageable);
        return ResponseEntity.ok(result);
    }

    }
