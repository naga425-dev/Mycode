package com.example.assignment.error;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final long serialVersionUID = 1L;

    @ExceptionHandler(value = BadRequestError.class)
    public ResponseEntity<Error> exception(BadRequestError exception) {
        Error error = new Error(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Error> exception(Exception exception) {
        Error error = new Error(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    public static class Error{
        private String message;
        private int errorCode;

        public Error(String message, int errorCode) {
            this.message = message;
            this.errorCode = errorCode;
        }
    }
}
