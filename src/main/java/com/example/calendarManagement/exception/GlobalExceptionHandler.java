package com.example.calendarManagement.exception;

import com.example.calendarManagement.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ResponseDTO> handleEmployeeMissingInputException(MissingFieldException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Invalid employee data provide", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ResponseDTO> handleEmployeeInvalidEmailException(InvalidFieldException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Fail to add employee", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NonUniqueFieldException.class)
    public ResponseEntity<ResponseDTO> handleNonUniqueEmployeeEmailException(NonUniqueFieldException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Fail to add employee", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO> handleNotFoundEmployeeException(NotFoundException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Employee not found", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public  ResponseEntity<ResponseDTO> handleConstraintViolationException(ConstraintViolationException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Constrain Violated", 409, null, error);
        return ResponseEntity.badRequest().body(response);
    }

}
