package com.example.calendarManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRoomResponseDTO {
    private int roomId;
    private String roomName;
    private int officeId;
    private boolean isEnable;

}
