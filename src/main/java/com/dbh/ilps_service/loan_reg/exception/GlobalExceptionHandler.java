package com.dbh.ilps_service.loan_reg.exception;

import com.dbh.ilps_service.loan_reg.utils.Constants;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.dbh.ilps_service.loan_reg.utils.ResponseBuilder.error;

@Slf4j
@ControllerAdvice(basePackages = "com.dbh.controller")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<JSONObject> handleResourceNotFoundExceptions(Exception ex) {
        return new ResponseEntity<>(error((ex.getMessage() + " " + Constants.RESOURCE_NOT_FOUND)).getJson(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<JSONObject> handleConstraintViolation(Exception ex, WebRequest request) {
        return new ResponseEntity<>(error((handleExceptionInternal(
                ex, ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request))).getJson(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<JSONObject> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error(ex.getMessage()).getJson());
    }
}
