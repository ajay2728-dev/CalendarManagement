package com.example.calendarManagement.dto;

import java.util.List;

public class MeetingRequestDTO {
    private int meetingId;
    private String  description;
    private String agenda;
    private List<Integer> employeeIDs;
    private String startTime;
    private  String endTime;
    private int roomId;

    public MeetingRequestDTO(int meetingId, String description, String agenda, List<Integer> employeeIDs, String startTime, String endTime, int roomId) {
        this.meetingId = meetingId;
        this.description = description;
        this.agenda = agenda;
        this.employeeIDs = employeeIDs;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
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

    public List<Integer> getEmployeeIDs() {
        return employeeIDs;
    }

    public void setEmployeeIDs(List<Integer> employeeIDs) {
        this.employeeIDs = employeeIDs;
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
