package com.example.calendarManagement.validator;


import com.example.calendarManagement.dto.MeetingStatusDTO;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.InvalidFieldException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.EmployeeMeetingStatusModel;
import com.example.calendarManagement.repository.EmployeeMeetingStatusRepo;
import com.example.thriftMeeting.IMeetingServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class MeetingValidator {

    @Autowired
    private EmployeeMeetingStatusRepo employeeMeetingStatusRepo;

    public void canScheduleValidator(IMeetingServiceDTO meetingDTO) throws InvalidFieldException, MissingFieldException, ConstraintViolationException {

        // check missing input
        if( meetingDTO.getEmployeeIDs().size()==0 || meetingDTO.getStartTime()==null || meetingDTO.getEndTime()==null ){
            throw new MissingFieldException(" Missing employeeIDs or startTime or endTime ");
        }

        // number of employee if less 6
        if(meetingDTO.getEmployeeIDs().size()<6){
            throw new ConstraintViolationException("Number of Employee is less than 6");
        }

        // Parse startTime and endTime as LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(meetingDTO.getStartTime(), formatter);
        LocalDateTime end = LocalDateTime.parse(meetingDTO.getEndTime(), formatter);

        // Ensure startTime and endTime are on the same day
        if (!start.toLocalDate().isEqual(end.toLocalDate())) {
            throw new InvalidFieldException("Start time and end time must be on the same day.");
        }

        // Get the LocalTime part from start and end time
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        // Check if startTime and endTime are between 10 AM and 6 PM
        LocalTime tenAM = LocalTime.of(10, 0);
        LocalTime sixPM = LocalTime.of(18, 0);

        if (startTime.isBefore(tenAM) || startTime.isAfter(sixPM)) {
            throw new InvalidFieldException("Start time must be between 10 AM and 6 PM.");
        }

        if (endTime.isBefore(tenAM) || endTime.isAfter(sixPM)) {
            throw new InvalidFieldException("End time must be between 10 AM and 6 PM.");
        }

        // If the meeting is scheduled for today, ensure the start time is in the future
        LocalDateTime now = LocalDateTime.now();
        if (start.isBefore(now)) {
            throw new InvalidFieldException("Meeting cannot be scheduled in the past.");
        }

        // Check if the difference between startTime and endTime is at least 30 minutes
        Duration duration = Duration.between(start, end);
        if (duration.toMinutes() < 30) {
            throw new InvalidFieldException("The meeting duration must be at least 30 minutes.");
        }

    }

    public EmployeeMeetingStatusModel updateEmployeeMeetingStatusValidator (MeetingStatusDTO meetingStatusDTO){
        // validation check
        if(meetingStatusDTO.getMeetingId()==0 || meetingStatusDTO.getEmployeeId()==0 || meetingStatusDTO.isStatus()==null){
            throw new MissingFieldException("Input Field for update meeting status is missing");
        }

        int employeeId = meetingStatusDTO.getEmployeeId();
        int meetingId = meetingStatusDTO.getMeetingId();

        // check meetingId and employeeId in meeting status model
        Optional<EmployeeMeetingStatusModel> exitingMeetingStatusOpt = employeeMeetingStatusRepo.findMeetingStatusByEmployeeAndMeeting(employeeId,meetingId);

        if(!exitingMeetingStatusOpt.isPresent()){
            throw new NotFoundException("meeting status not found with give employeeId and meetingId");
        }

        EmployeeMeetingStatusModel existingMeetingStatus = exitingMeetingStatusOpt.get();

        String status = meetingStatusDTO.isStatus() ? "Accepted" : "Rejected";

        if(existingMeetingStatus.getMeetingStatus() == meetingStatusDTO.isStatus()){
            throw new ConstraintViolationException("Employee already "+ status +" the meeting.");
        }

        return existingMeetingStatus;

    }

}
