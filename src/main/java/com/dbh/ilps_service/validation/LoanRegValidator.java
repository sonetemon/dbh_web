package com.dbh.ilps_service.validation;

import com.dbh.ilps_service.dto.request.LoanRegRequest;
import com.dbh.ilps_service.entity.LoanReg;
import com.dbh.ilps_service.repository.LoanRegRepository;
import com.dbh.ilps_service.service.LoanRegService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.dbh.ilps_service.utils.Constants.ALREADY_EXIST;
import static com.dbh.ilps_service.utils.StringUtils.nonNull;

@Component
@RequiredArgsConstructor
public class LoanRegValidator implements Validator {
    private final LoanRegService loanRegService;

    private final LoanRegRepository loanRegRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return LoanRegRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoanRegRequest loanRegRequest = (LoanRegRequest) target;
 /*       LoanReg loanReg = loanRegService.findByFileNo(loanRegRequest.getFileNo());
        if (nonNull(loanReg)){
            errors.reject("fileNo", null, ALREADY_EXIST);*/

        // Check if username is empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fileNo", "field.required", "File No is required.");

        // Check if username already exists
        if (loanRegService.isUFileNoTaken(loanRegRequest.getFileNo())) {
            errors.rejectValue("fileNo", "field.unique", "File No is already taken.");
        }
    }
}
