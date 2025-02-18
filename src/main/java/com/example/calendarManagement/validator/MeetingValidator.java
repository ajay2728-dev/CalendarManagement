package com.example.calendarManagement.validator;


import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.InvalidFieldException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.thriftMeeting.IMeetingServiceDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class MeetingValidator {
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

        // Check if the difference between startTime and endTime is at least 30 minutes
        Duration duration = Duration.between(start, end);
        if (duration.toMinutes() < 30) {
            throw new InvalidFieldException("The meeting duration must be at least 30 minutes.");
        }

    }

    public void meetingScheduleValidator(IMeetingServiceDTO meetingDTO) throws MissingFieldException, ConstraintViolationException, InvalidFieldException {

        // check missing input
        if(  meetingDTO.getAgenda()==null || meetingDTO.getDescription()==null || meetingDTO.getEmployeeIDs().size()==0 || meetingDTO.getStartTime()==null || meetingDTO.getEndTime()==null ){
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

        // Check if the difference between startTime and endTime is at least 30 minutes
        Duration duration = Duration.between(start, end);
        if (duration.toMinutes() < 30) {
            throw new InvalidFieldException("The meeting duration must be at least 30 minutes.");
        }
    }
}
