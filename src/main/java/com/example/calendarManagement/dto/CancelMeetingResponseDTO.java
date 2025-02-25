package com.example.calendarManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CancelMeetingResponseDTO {
    private int meetingId;
    private Boolean status;

    public CancelMeetingResponseDTO(int meetingId, Boolean status) {
        this.meetingId = meetingId;
        this.status = status;
    }

}
