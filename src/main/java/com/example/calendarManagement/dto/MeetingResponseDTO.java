package com.example.calendarManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingResponseDTO {
    private int meetingId;
    private String  description;
    private String agenda;
    private List<Integer> employeeIDs;
    private String startTime;
    private  String endTime;
    private int roomId;

}
