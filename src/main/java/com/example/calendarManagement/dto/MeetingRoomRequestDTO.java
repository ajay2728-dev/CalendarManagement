package com.example.calendarManagement.dto;

public class MeetingRoomRequestDTO {
    private int roomId;
    private String roomName;
    private int office_id;
    private Boolean isEnable;

    public MeetingRoomRequestDTO(int roomId, String roomName, int office_id, Boolean isEnable) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.office_id = office_id;
        this.isEnable = (isEnable!=null) ? isEnable : true;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
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

    public int getOffice_id() {
        return office_id;
    }

    public void setOffice_id(int office_id) {
        this.office_id = office_id;
    }
}
