package com.example.calendarManagement.exception;

public class MissingFieldException extends  RuntimeException{
    public MissingFieldException(String message) {
        super(message);
    }
}
