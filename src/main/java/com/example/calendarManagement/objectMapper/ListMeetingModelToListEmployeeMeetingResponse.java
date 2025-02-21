package com.example.calendarManagement.objectMapper;

import com.example.calendarManagement.dto.EmployeeMeetingResponseDTO;
import com.example.calendarManagement.model.MeetingModel;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ListMeetingModelToListEmployeeMeetingResponse {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static List<EmployeeMeetingResponseDTO> mapToEmployeeMeetingResponseDTO(List<MeetingModel> meetings) {

        return meetings.stream()
                .map(meeting -> new EmployeeMeetingResponseDTO(
                        meeting.getMeetingId(),
                        meeting.getDescription(),
                        meeting.getAgenda(),
                        meeting.getStartTime().format(FORMATTER),
                        meeting.getEndTime().format(FORMATTER),
                        meeting.getMeetingRoom().getRoomId()
                ))
                .collect(Collectors.toList());
    }
}
