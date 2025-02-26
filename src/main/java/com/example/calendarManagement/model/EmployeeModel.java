package com.example.calendarManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

    @Id
    @Column(name = "employeeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(name = "employeeName",nullable = false)
    private String employeeName;

    @Column(name = "employeeEmail", unique = true, nullable = false)
    private String employeeEmail;

    @ManyToOne
    @JoinColumn(name = "officeId", nullable = false)
    private OfficeModel office;

    @Column(name = "department",nullable = false)
    private String department;

    @Column(name = "salary",nullable = false)
    private int salary;

    @Column(name = "isActive",nullable = false)
    Boolean isActive;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeMeetingStatusModel> meetingStatuses;

    public EmployeeModel(int employeeId,String employeeName, String employeeEmail, OfficeModel office, String department, Boolean isActive, int salary) {
        this.employeeId=employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.office = office;
        this.department = department;
        this.isActive = isActive;
        this.salary = salary;
    }

    public EmployeeModel(String employeeName, String employeeEmail, OfficeModel office, String department, Boolean isActive, int salary) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.office = office;
        this.department = department;
        this.isActive = isActive;
        this.salary = salary;
    }

}
