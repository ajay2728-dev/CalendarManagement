package com.example.calendarManagement.dto;

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

    public EmployeeMeetingResponseDTO(){

    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
