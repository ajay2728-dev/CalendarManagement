package com.example.calendarManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingStatusDTO {

    private int meetingId;
    private int employeeId;
    private Boolean meetingStatus;
}
