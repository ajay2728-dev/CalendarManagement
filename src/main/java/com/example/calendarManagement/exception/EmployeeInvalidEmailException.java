package com.example.calendarManagement.exception;

public class EmployeeInvalidEmailException extends RuntimeException{
    public EmployeeInvalidEmailException(String message) {
        super(message);
    }
}
