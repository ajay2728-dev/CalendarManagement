package com.example.calendarManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {

    private int employeeId;
    private String employeeName;
    private int officeId;
    private String employeeEmail;
    private Boolean isActive;
    private int salary;
    private String department;

    public EmployeeResponseDTO(int employeeId, String employeeName, int officeId, String employeeEmail, int salary, Boolean isActive, String department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.officeId = officeId;
        this.employeeEmail = employeeEmail;
        this.salary = salary;
        this.isActive = isActive;
        this.department = department;
    }

}
