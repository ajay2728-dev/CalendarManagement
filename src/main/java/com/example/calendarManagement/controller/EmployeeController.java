package com.example.calendarManagement.controller;

import com.example.calendarManagement.dto.ResponseDTO;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/api/employee")
    public ResponseEntity<ResponseDTO> addEmployee(@RequestBody EmployeeModel employee){
        log.info("add employee controller..... ");

        Map<String, Object> data = new HashMap<>();
        EmployeeModel body = employeeService.addEmployee(employee);
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Employee added successfully",201,data,null);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/api/employee/{employeeId}")
    public ResponseEntity<ResponseDTO> getEmployeeById(@PathVariable int employeeId){
        log.info("getting employee with Id ");

        Map<String, Object> data = new HashMap<>();
        EmployeeModel body = employeeService.getEmployeeById(employeeId);
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Employee retrieved successfully",200,data,null);

        return ResponseEntity.ok(responseBody);
    }
}
