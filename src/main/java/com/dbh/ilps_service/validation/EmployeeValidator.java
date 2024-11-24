package com.dbh.ilps_service.validation;

import com.dbh.ilps_service.dto.request.EmployeeRequest;
import com.dbh.ilps_service.entity.Employee;
import com.dbh.ilps_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.dbh.ilps_service.utils.Constants.ALREADY_EXIST;
import static com.dbh.ilps_service.utils.StringUtils.nonNull;

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
        if (nonNull(employee)) {
            errors.reject("email", null, ALREADY_EXIST);
        }
    }
}
