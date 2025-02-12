package com.example.calendarManagement.dto;

public class EmployeeRequestDTO {
    private int employeeId;
    private String employeeName;
    private String officeLocation;
    private String employeeEmail;
    private Boolean active;
    private int salary;
    private String department;

    public EmployeeRequestDTO(int employeeId,String employeeName, String officeLocation, String employeeEmail, Boolean active, int salary, String department) {

        this.employeeName = employeeName;
        this.officeLocation = officeLocation;
        this.employeeEmail = employeeEmail;
        this.active = active;
        this.salary = salary;
        this.department = department;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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