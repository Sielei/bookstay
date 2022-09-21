package com.bstay.common.app.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception exception){
        return ErrorDto.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .errorMessage(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(ValidationException validationException) {
        ErrorDto errorDto;
        if (validationException instanceof ConstraintViolationException) {
            String violations = extractViolationsFromException((ConstraintViolationException) validationException);
            errorDto = ErrorDto.builder()
                    .errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .errorMessage(violations)
                    .build();
        } else {
            String exceptionMessage = validationException.getMessage();
            errorDto = ErrorDto.builder()
                    .errorCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .errorMessage(exceptionMessage)
                    .build();
        }
        return errorDto;
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }
}
