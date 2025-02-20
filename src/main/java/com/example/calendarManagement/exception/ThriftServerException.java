package com.example.calendarManagement.exception;

import org.springframework.http.HttpStatus;

public class ThriftServerException extends RuntimeException {
  private final int status;

  public ThriftServerException(String message, int status) {
    super(message);
    this.status = status;
  }

  public int getStatus() {
    return status;
  }
}
