package com.example.calendarManagement.controller;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.dto.EmployeeResponseDTO;
import com.example.calendarManagement.dto.ResponseDTO;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.service.EmployeeService;
import com.example.calendarManagement.validator.EmployeeValidator;
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

    @Autowired
    EmployeeValidator employeeValidator;

    @PostMapping
    public ResponseEntity<ResponseDTO> addEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO){
        log.info("add employee controller  ...");

        log.info("validation for adding employee ...");
        OfficeModel office = employeeValidator.addEmployeeValidator(employeeRequestDTO);
        log.info("validation done ...");

        log.info("adding employee ...");
        EmployeeResponseDTO body = employeeService.addEmployee(employeeRequestDTO, office);
        log.info("employee added ...");

        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Employee added successfully",201,data,null);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllEmployee(){
        log.info("getting all employee controller ...");

        log.info("fetching all employee details ...");
        List<EmployeeResponseDTO> body = employeeService.getAllEmployee();
        log.info("fetch employee details ...");

        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Employee retrieved successfully",200,data,null);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ResponseDTO> getEmployeeById(@PathVariable int employeeId){
        log.info("getting employee controller ...");

        EmployeeResponseDTO body = employeeService.getEmployeeById(employeeId);
        Map<String, Object> data = new HashMap<>();
        data.put("employee",body);
        ResponseDTO responseBody = new ResponseDTO("Employee retrieved successfully",200,data,null);

        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<ResponseDTO> deleteEmployeeById(@PathVariable int employeeId) throws Exception {
        log.info("delete employee controller ...");

        log.info("validating the employee detail ...");
        EmployeeModel validEmployee = employeeValidator.deleteEmployeeValidator(employeeId);
        log.info("validation is done ...");

        log.info("deleting the employee data");
        EmployeeModel body = employeeService.deleteEmployeeById(validEmployee);
        log.info("deleted the employee data");

        Map<String, Integer> data = new HashMap<>();
        data.put("employeeId",body.getEmployeeId());
        ResponseDTO responseBody = new ResponseDTO("Employee deleted successfully",200,data,null);

        return ResponseEntity.ok(responseBody);
    }

}
