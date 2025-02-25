package com.example.calendarManagement.dto;

public class MeetingRoomResponseDTO {
    private int roomId;
    private String roomName;
    private int officeId;
    private boolean isEnable;

    public MeetingRoomResponseDTO(int roomId, String roomName, int officeId, boolean isEnable) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.officeId = officeId;
        this.isEnable = isEnable;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
