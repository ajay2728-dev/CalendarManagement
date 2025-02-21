package com.example.calendarManagement.service;


import com.example.calendarManagement.dto.CancelMeetingResponseDTO;
import com.example.calendarManagement.dto.EmployeeMeetingResponseDTO;
import com.example.calendarManagement.dto.MeetingResponseDTO;
import com.example.calendarManagement.dto.MeetingStatusDTO;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.MeetingModel;
import com.example.calendarManagement.model.MeetingStatusModel;
import com.example.calendarManagement.objectMapper.ListMeetingModelToListEmployeeMeetingResponse;
import com.example.calendarManagement.objectMapper.MeetingModelTOMeetingResponseDTO;
import com.example.calendarManagement.repository.EmployeeRepo;
import com.example.calendarManagement.repository.MeetingRepo;
import com.example.calendarManagement.repository.MeetingStatusRepo;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {

    @Autowired
    private MeetingStatusRepo meetingStatusRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private MeetingRepo meetingRepo;

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

    public MeetingResponseDTO getMeetingById(int meetingId) {
        // check meeting is valid or not
        Optional<MeetingModel> meetingOpt = meetingRepo.findById(meetingId);

        if(!meetingOpt.isPresent()){
            throw  new NotFoundException("meeting not found with given meetingId");
        }

        MeetingModel meeting = meetingOpt.get();
        MeetingResponseDTO response = MeetingModelTOMeetingResponseDTO.map(meeting);

        // return the meeting
        return response;
    }

    @Transactional
    public CancelMeetingResponseDTO cancelMeetingById(int meetingId) {
        // check valid meeting id
        Optional<MeetingModel> meetingModelOpt = meetingRepo.findValidMeeting(meetingId);
        if(!meetingModelOpt.isPresent()){
            throw new NotFoundException("Meeting not found with given id " + meetingId);
        }

        MeetingModel existingMeeting = meetingModelOpt.get();
        meetingStatusRepo.updateMeetingStatusToFalse(meetingId);
        existingMeeting.setValid(false);
        meetingRepo.save(existingMeeting);

        CancelMeetingResponseDTO response = new CancelMeetingResponseDTO(meetingId,false);
        return response;
    }


    public List<EmployeeMeetingResponseDTO> getEmployeeMeeting(int employeeId, String filterType, LocalDateTime startDate, LocalDateTime endDate) {

        if(!employeeRepo.findById(employeeId).isPresent()){
            throw new NotFoundException("Employee not found with given ID");
        }

        List<MeetingModel> meetings;

        switch (filterType.toLowerCase()) {
            case "current_week":
                LocalDateTime startOfWeek = LocalDateTime.now().with(java.time.DayOfWeek.MONDAY);
                LocalDateTime endOfWeek = startOfWeek.plusDays(6);
                meetings = meetingRepo.findByEmployeeIdAndDateRange(employeeId, startOfWeek, endOfWeek);
                break;

            case "last_week":
                LocalDateTime startOfLastWeek = LocalDateTime.now().minusWeeks(1).with(java.time.DayOfWeek.MONDAY);
                LocalDateTime endOfLastWeek = startOfLastWeek.plusDays(6);
                meetings = meetingRepo.findByEmployeeIdAndDateRange(employeeId, startOfLastWeek, endOfLastWeek);
                break;

            case "custom_range":
                if (startDate == null || endDate == null) {
                    throw new MissingFieldException("startDate and endDate is not provided");
                }
                meetings = meetingRepo.findByEmployeeIdAndDateRange(employeeId, startDate, endDate);
                break;

            default: // "today"
                LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
                LocalDateTime todayEnd = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
                meetings = meetingRepo.findByEmployeeIdAndDateRange(employeeId, todayStart, todayEnd);
        }

        List<EmployeeMeetingResponseDTO> responseList = ListMeetingModelToListEmployeeMeetingResponse.mapToEmployeeMeetingResponseDTO(meetings);
        return responseList;
    }
}
