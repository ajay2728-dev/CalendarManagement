package com.example.calendarManagement.objectMapper;

import com.example.calendarManagement.dto.MeetingRequestDTO;
import com.example.thriftMeeting.IMeetingServiceDTO;

public class IMeetingToMeetingRequest {
    public static MeetingRequestDTO map(IMeetingServiceDTO meetingServiceDTO){
        MeetingRequestDTO meetingRequestDTO = new MeetingRequestDTO(meetingServiceDTO.getMeetingId(),
                meetingServiceDTO.getDescription(),meetingServiceDTO.getAgenda(),
                meetingServiceDTO.getEmployeeIDs(), meetingServiceDTO.getStartTime(),
                meetingServiceDTO.getEndTime(), meetingServiceDTO.getRoomId());

        return meetingRequestDTO;
    }
}
