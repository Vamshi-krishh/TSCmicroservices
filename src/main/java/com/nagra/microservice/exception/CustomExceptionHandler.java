package com.nagra.microservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        String errorMessage = ex.getReason() != null ? ex.getReason() : ex.getMessage();
        Map<String, String> error = new LinkedHashMap<>();
        error.put("error", errorMessage);
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        String errorMessage = "Internal Server Error";
        Map<String, String> error = new LinkedHashMap<>();
        error.put("error", errorMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
