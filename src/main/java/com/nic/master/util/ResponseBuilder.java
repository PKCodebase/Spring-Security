package com.nic.master.util;

import com.nic.master.param.StatusParam;
import com.nic.master.param.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

public class ResponseBuilder {

    private ResponseBuilder() {
        //Private constructor to hide public constructor
    }

    public static ResponseEntity<Object> buildCreated(StatusParam status, Object successBody, HttpServletRequest request) {
        if (status == null || status.getStatus()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(successBody);
        } else {
            return handleStatus(status, request.getRequestURI());
        }
    }

    public static ResponseEntity<Object> buildOk(StatusParam status, Object successBody, HttpServletRequest request) {
        if (status == null || status.getStatus()) {
            return ResponseEntity.ok(successBody);
        } else {
            return handleStatus(status, request.getRequestURI());
        }
    }

    private static ResponseEntity<Object> handleStatus(StatusParam status, String path) {
        String message = status.getMessage().toLowerCase();
        HttpStatus statusCode;

        if (message.contains("not found")) {
            statusCode = HttpStatus.NOT_FOUND;
        } else if (message.contains("already exists")) {
            statusCode = HttpStatus.CONFLICT;
        } else {
            statusCode = HttpStatus.BAD_REQUEST;
        }

        ApiError error = new ApiError(
                new Date(),
                String.valueOf(statusCode.value()),
                statusCode.getReasonPhrase(),
                path,
                "Error Status Code : " + statusCode.value(),
                status.getMessage()
        );

        return ResponseEntity.status(statusCode).body(error);
    }

    public static ResponseEntity<Object> buildError(HttpStatus status, String path, String message) {
        ApiError error = new ApiError(
                new Date(),
                String.valueOf(status.value()),
                status.getReasonPhrase(),
                path,
                "Error Status Code :  " + status.value(),
                message
        );
        return ResponseEntity.status(status).body(error);
    }
}



