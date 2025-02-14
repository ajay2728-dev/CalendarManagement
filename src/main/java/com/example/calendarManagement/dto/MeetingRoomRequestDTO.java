package com.example.calendarManagement.dto;

public class MeetingRoomRequestDTO {
    private int roomId;
    private String roomName;
    private int officeId;
    private Boolean isEnable;

    public MeetingRoomRequestDTO(int roomId, String roomName, int officeId, Boolean isEnable) {
        this.roomId=roomId;
        this.roomName = roomName;
        this.officeId = officeId;
        this.isEnable = (isEnable!=null) ? isEnable : true;
    }

    public MeetingRoomRequestDTO(String roomName, int officeId, Boolean isEnable) {
        this.roomName = roomName;
        this.officeId = officeId;
        this.isEnable = (isEnable!=null) ? isEnable : true;
    }

    public MeetingRoomRequestDTO(){

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
        return officeId;
    }

    public void setOffice_id(int officeId) {
        this.officeId = officeId;
    }
}
