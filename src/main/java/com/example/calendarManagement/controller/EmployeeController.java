package com.example.calendarManagement.controller;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.dto.ResponseDTO;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO){
        log.info("add employee... ");

        EmployeeModel body = employeeService.addEmployee(employeeRequestDTO);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Employee added successfully",201,data,null);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllEmployee(){
        log.info("getting all employee info");

        List<EmployeeModel> body = employeeService.getAllEmployee();
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Employee retrieved successfully",200,data,null);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ResponseDTO> getEmployeeById(@PathVariable int employeeId){

        EmployeeModel body = employeeService.getEmployeeById(employeeId);
        Map<String, Object> data = new HashMap<>();
        data.put("employee",body);
        ResponseDTO responseBody = new ResponseDTO("Employee retrieved successfully",200,data,null);

        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<ResponseDTO> deleteEmployeeById(@PathVariable int employeeId) throws Exception {
        log.info("delete employee by Id");

        EmployeeModel body = employeeService.deleteEmployeeById(employeeId);
        Map<String, Integer> data = new HashMap<>();
        data.put("employeeId",body.getEmployeeId());
        ResponseDTO responseBody = new ResponseDTO("Employee deleted successfully",200,data,null);

        return ResponseEntity.ok(responseBody);
    }

}
