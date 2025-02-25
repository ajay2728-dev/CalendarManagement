package com.example.calendarManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeMeetingResponseDTO {
    private int meetingId;
    private String description;
    private String agenda;
    private String startTime;
    private  String endTime;
    private int roomId;

    public EmployeeMeetingResponseDTO(int meetingId, String description, String agenda, String startTime, String endTime, int roomId) {
        this.meetingId = meetingId;
        this.description = description;
        this.agenda = agenda;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
    }

}
