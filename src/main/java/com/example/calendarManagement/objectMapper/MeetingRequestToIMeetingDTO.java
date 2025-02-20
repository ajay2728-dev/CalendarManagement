package com.example.calendarManagement.objectMapper;

import com.example.calendarManagement.dto.MeetingRequestDTO;
import com.example.thriftMeeting.IMeetingServiceDTO;

public class MeetingRequestToIMeetingDTO {

    public static IMeetingServiceDTO map(MeetingRequestDTO meetingRequestData){
            IMeetingServiceDTO meetingServiceDTO = new IMeetingServiceDTO(meetingRequestData.getMeetingId(),meetingRequestData.getDescription(),
                    meetingRequestData.getAgenda(), meetingRequestData.getEmployeeIDs(), meetingRequestData.getStartTime(),
                    meetingRequestData.getEndTime(), meetingRequestData.getRoomId());

            return meetingServiceDTO;
    }
}
