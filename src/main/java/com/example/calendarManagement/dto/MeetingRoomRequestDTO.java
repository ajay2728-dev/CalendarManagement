package com.example.calendarManagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRoomRequestDTO {
    private int roomId;
    private String roomName;
    private int officeId;
    @JsonProperty("isEnable")
    private boolean isEnable;

}
