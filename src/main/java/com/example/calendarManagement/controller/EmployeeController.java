package com.example.calendarManagement.controller;

import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/api/employee")
    public EmployeeModel addEmployee(@RequestBody EmployeeModel employee){
        log.info("add employee controller..... ");
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/api/employee/{employeeId}")
    public EmployeeModel getEmployeeById(@PathVariable int employeeId){
        log.info("getting employee with Id ");
        return employeeService.getEmployeeById(employeeId);
    }
}
