package com.example.assignment.error;

public class BadRequestError extends RuntimeException{
    public BadRequestError(String message) {
        super(message);
    }
}
