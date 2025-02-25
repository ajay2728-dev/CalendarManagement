package com.example.calendarManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeMeetingStatusModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "meetingId", nullable = false)
    private MeetingModel meeting;

    @Column(name = "meetingStatus", nullable = false)
    private Boolean meetingStatus;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private EmployeeModel employee;


    public EmployeeMeetingStatusModel(MeetingModel meeting, Boolean meetingStatus, EmployeeModel employee) {
        this.meeting = meeting;
        this.meetingStatus = meetingStatus;
        this.employee = employee;
    }

}
