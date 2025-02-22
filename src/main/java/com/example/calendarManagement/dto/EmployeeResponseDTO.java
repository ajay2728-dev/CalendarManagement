package com.example.calendarManagement.dto;

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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
