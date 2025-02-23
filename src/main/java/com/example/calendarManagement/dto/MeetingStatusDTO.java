package com.example.calendarManagement.dto;

public class MeetingStatusDTO {

    private int meetingId;
    private int employeeId;
    private Boolean meetingStatus;

    public MeetingStatusDTO(int meetingId, int employeeId, Boolean meetingStatus) {
        this.meetingId = meetingId;
        this.employeeId = employeeId;
        this.meetingStatus = meetingStatus;
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
        return meetingStatus;
    }

    public void setStatus(Boolean meetingStatus) {
        this.meetingStatus = meetingStatus;
    }
}
