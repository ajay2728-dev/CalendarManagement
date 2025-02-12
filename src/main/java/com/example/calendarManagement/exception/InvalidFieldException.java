package com.example.calendarManagement.exception;

public class InvalidFieldException extends RuntimeException{
    public InvalidFieldException(String message) {
        super(message);
    }
}
