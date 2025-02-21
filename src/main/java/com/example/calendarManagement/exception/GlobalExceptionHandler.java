package com.example.calendarManagement.exception;

import com.example.calendarManagement.dto.ResponseDTO;
import com.example.thriftMeeting.MeetingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MeetingException.class)
    public ResponseEntity<ResponseDTO> MeetingException(MeetingException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());
        ResponseDTO response = new ResponseDTO("Error from meeting scheduling", ex.getErrorCode(), null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ThriftServerException.class)
    public ResponseEntity<ResponseDTO> handleThriftException( ThriftServerException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());
        ResponseDTO response = new ResponseDTO("Error from Thrift server", ex.getStatus(), null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ResponseDTO> handleEmployeeMissingInputException(MissingFieldException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Missing Input Request Data error", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ResponseDTO> handleEmployeeInvalidEmailException(InvalidFieldException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Invalid Request Data Error ", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

//    @ExceptionHandler(NonUniqueFieldException.class)
//    public ResponseEntity<ResponseDTO> handleNonUniqueEmployeeEmailException(NonUniqueFieldException ex){
//        Map<String, Object> error = new HashMap<>();
//        error.put("detail", ex.getMessage());
//
//        ResponseDTO response = new ResponseDTO("Non Unique Data Added Error ", 400, null, error);
//        return ResponseEntity.badRequest().body(response);
//    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO> handleNotFoundEmployeeException(NotFoundException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Not Found Error", 400, null, error);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public  ResponseEntity<ResponseDTO> handleConstraintViolationException(ConstraintViolationException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("detail", ex.getMessage());

        ResponseDTO response = new ResponseDTO("Constrain Violated Error", 409, null, error);
        return ResponseEntity.badRequest().body(response);
    }

//    @ExceptionHandler(NullPointerException)

}
