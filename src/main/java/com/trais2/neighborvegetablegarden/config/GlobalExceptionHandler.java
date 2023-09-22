package com.trais2.neighborvegetablegarden.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleBindException(BindException e) {
        String errorMessage = getFirstErrorMessage(e.getBindingResult());
        if (errorMessage == null) {
            errorMessage = "Request error";
        }
        return ResponseEntity.badRequest().body(errorMessage);
    }

    public String getFirstErrorMessage(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            return fieldError.getDefaultMessage();
        }
        return null;
    }
}
