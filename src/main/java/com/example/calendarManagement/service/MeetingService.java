package com.example.calendarManagement.service;


import com.example.calendarManagement.dto.MeetingStatusDTO;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.MeetingStatusModel;
import com.example.calendarManagement.repository.MeetingStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetingService {

    @Autowired
    private MeetingStatusRepo meetingStatusRepo;

    public MeetingStatusDTO updateStatusMeeting(MeetingStatusDTO meetingStatusDTO) {

        // validation check
        if(meetingStatusDTO.getMeetingId()==0 || meetingStatusDTO.getEmployeeId()==0 || meetingStatusDTO.isStatus()==null){
            throw new MissingFieldException("Input Field for update meeting status is missing");
        }

        int employeeId = meetingStatusDTO.getEmployeeId();
        int meetingId = meetingStatusDTO.getMeetingId();

        // check meetingId and employeeId in meeting status model
        Optional<MeetingStatusModel> exitingMeetingStatusOpt = meetingStatusRepo.findMeetingStatusByEmployeeAndMeeting(employeeId,meetingId);

        if(!exitingMeetingStatusOpt.isPresent()){
            throw new NotFoundException("meeting status not found with give employeeId and meetingId");
        }

        MeetingStatusModel exitingMeetingStatus = exitingMeetingStatusOpt.get();

        // update meeting status
        exitingMeetingStatus.setStatus(meetingStatusDTO.isStatus());
        meetingStatusRepo.save(exitingMeetingStatus);

        // return meeting status
        MeetingStatusDTO updatedMeetingStatus = new MeetingStatusDTO(meetingStatusDTO.getMeetingId(),
                meetingStatusDTO.getEmployeeId(),
                exitingMeetingStatus.getStatus());

        return updatedMeetingStatus;
    }
}
