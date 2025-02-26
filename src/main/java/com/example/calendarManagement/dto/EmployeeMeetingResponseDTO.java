package com.example.calendarManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeMeetingResponseDTO {
    private int meetingId;
    private String description;
    private String agenda;
    private String startTime;
    private  String endTime;
    private int roomId;

}
