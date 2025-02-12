package com.example.calendarManagement.exception;

import com.example.calendarManagement.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeMissingInputException.class)
    public ResponseEntity<ResponseDTO> handleEmployeeMissingInputException(EmployeeMissingInputException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Invalid employee data provide", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EmployeeInvalidEmailException.class)
    public ResponseEntity<ResponseDTO> handleEmployeeInvalidEmailException(EmployeeInvalidEmailException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Fail to add employee", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NonUniqueEmployeeEmailException.class)
    public ResponseEntity<ResponseDTO> handleNonUniqueEmployeeEmailException(NonUniqueEmployeeEmailException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Fail to add employee", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundEmployeeException.class)
    public ResponseEntity<ResponseDTO> handleNotFoundEmployeeException(NotFoundEmployeeException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Employee not found", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

}
