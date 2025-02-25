package com.example.calendarManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRequestDTO {
    private int employeeId;
    private String employeeName;
    private int officeId;
    private String employeeEmail;
    private Boolean isActive;
    private int salary;
    private String department;

    public EmployeeRequestDTO(int employeeId, String employeeName, int officeId, String employeeEmail, Boolean isActive, int salary, String department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.officeId = officeId;
        this.employeeEmail = employeeEmail;
        this.isActive = isActive;
        this.salary = salary;
        this.department = department;
    }

}