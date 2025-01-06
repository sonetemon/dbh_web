package com.dbh.ilps_service.loan_reg.validation;

import com.dbh.ilps_service.loan_reg.dto.request.EmployeeRequest;
import com.dbh.ilps_service.loan_reg.entity.Employee;
import com.dbh.ilps_service.loan_reg.service.EmployeeService;
import com.dbh.ilps_service.loan_reg.utils.Constants;
import com.dbh.ilps_service.loan_reg.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class EmployeeValidator implements Validator {

    private final EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeRequest request = (EmployeeRequest) target;
        Employee employee = employeeService.findByEmail(request.getEmail());
        if (StringUtils.nonNull(employee)) {
            errors.reject("email", null, Constants.ALREADY_EXIST);
        }
    }
}
