package com.example.calendarManagement.dto;

public class CancelMeetingResponseDTO {
    private int meetingId;
    private Boolean status;

    public CancelMeetingResponseDTO(int meetingId, Boolean status) {
        this.meetingId = meetingId;
        this.status = status;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
