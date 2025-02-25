package com.example.calendarManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRequestDTO {
    private int meetingId;
    private String  description;
    private String agenda;
    private List<Integer> employeeIDs;
    private String startTime;
    private  String endTime;
    private int roomId;


}
