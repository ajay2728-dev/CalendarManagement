package com.example.calendarManagement.exception;

public class NonUniqueEmployeeEmailException extends RuntimeException {
    public NonUniqueEmployeeEmailException(String message) {
        super(message);
    }
}
