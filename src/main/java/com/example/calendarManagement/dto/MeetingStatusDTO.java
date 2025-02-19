package com.example.calendarManagement.dto;

public class MeetingStatusDTO {

    private int meetingId;
    private int employeeId;
    private Boolean status;

    public MeetingStatusDTO(int meetingId, int employeeId, Boolean status) {
        this.meetingId = meetingId;
        this.employeeId = employeeId;
        this.status = status;
    }

    public MeetingStatusDTO() {

    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
