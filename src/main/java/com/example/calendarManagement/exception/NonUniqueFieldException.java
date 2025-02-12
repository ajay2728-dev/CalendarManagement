package com.example.calendarManagement.exception;

public class NonUniqueFieldException extends RuntimeException {
    public NonUniqueFieldException(String message) {
        super(message);
    }
}
