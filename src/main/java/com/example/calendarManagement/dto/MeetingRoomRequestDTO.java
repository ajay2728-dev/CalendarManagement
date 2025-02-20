package com.example.calendarManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;


public class MeetingRoomRequestDTO {
    private int roomId;
    private String roomName;
    private int officeId;
    @JsonProperty("isEnable")
    private boolean isEnable;

    public MeetingRoomRequestDTO(int roomId, String roomName, int officeId, boolean isEnable) {
        this.roomId=roomId;
        this.roomName = roomName;
        this.officeId = officeId;
        this.isEnable = isEnable;
    }

    public MeetingRoomRequestDTO(String roomName, int officeId, Boolean isEnable) {
        this.roomName = roomName;
        this.officeId = officeId;
        this.isEnable = (isEnable!=null) ? isEnable : true;
    }

    public MeetingRoomRequestDTO(){

    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        this.isEnable = enable;
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
}
