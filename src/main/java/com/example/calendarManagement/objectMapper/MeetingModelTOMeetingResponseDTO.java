package com.example.calendarManagement.objectMapper;

import com.example.calendarManagement.dto.MeetingResponseDTO;
import com.example.calendarManagement.model.MeetingModel;

import java.util.stream.Collectors;

public class MeetingModelTOMeetingResponseDTO {
    public static MeetingResponseDTO map(MeetingModel meetingModel){
        return new MeetingResponseDTO(
                meetingModel.getMeetingId(),
                meetingModel.getDescription(),
                meetingModel.getAgenda(),
                meetingModel.getStatuses().stream()
                        .map(i -> i.getEmployee().getEmployeeId())
                        .collect(Collectors.toList()),
                meetingModel.getStartTime().toString(),
                meetingModel.getEndTime().toString(),
                meetingModel.getMeetingRoom().getRoomId()
        );
    }
}
