package com.nic.master.exception;

import com.nic.master.param.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            // ✅ Only include the first error per field (skip if already added)
            errors.putIfAbsent(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("message", "Validation failed");
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {

        ApiError error = new ApiError();
        error.setDatetime(new Date());
        error.setHttpStatus(HttpStatus.NOT_FOUND.toString()); // JSON me nahi jayega
        error.setHttpMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
        error.setPath(request.getDescription(false).replace("uri=", ""));
        error.setErrorCode("404");
        error.setErrorMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex) {
        ApiError error = new ApiError();
        error.setDatetime(new Date());
        error.setHttpStatus(HttpStatus.BAD_REQUEST.toString()); // JSON me nahi jayega
        error.setHttpMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        error.setErrorCode("400");
        error.setErrorMessage( ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
