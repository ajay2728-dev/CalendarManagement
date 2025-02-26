package com.example.calendarManagement.objectMapper;

import com.example.calendarManagement.dto.MeetingResponseDTO;
import com.example.calendarManagement.model.MeetingModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MeetingModelTOMeetingResponseDTO {
    public MeetingResponseDTO map(MeetingModel meetingModel){
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
