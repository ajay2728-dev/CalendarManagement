package com.example.calendarManagement.exception;

public class NotFoundEmployeeException extends RuntimeException {
    public NotFoundEmployeeException(String message) {
        super(message);
    }
}
