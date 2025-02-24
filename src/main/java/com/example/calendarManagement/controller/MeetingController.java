package com.example.calendarManagement.controller;

import com.example.calendarManagement.dto.*;
import com.example.calendarManagement.model.EmployeeMeetingStatusModel;
import com.example.calendarManagement.objectMapper.IMeetingToMeetingRequest;
import com.example.calendarManagement.objectMapper.MeetingRequestToIMeetingDTO;
import com.example.calendarManagement.service.MeetingService;
import com.example.calendarManagement.service.ThriftMeetingService;
import com.example.calendarManagement.validator.MeetingValidator;
import com.example.thriftMeeting.IMeetingServiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/meeting")
public class MeetingController {

    @Autowired
    ThriftMeetingService thriftMeetingService;

    @Autowired
    MeetingService meetingService;

    @Autowired
    MeetingValidator meetingValidator;


    @PostMapping("/can-schedule")
    public ResponseEntity<ResponseDTO> canScheduleMeeting(@RequestBody MeetingRequestDTO meetingDetails) throws TException {
        log.info("can schedule meeting");

        IMeetingServiceDTO  meetingServiceDTO = MeetingRequestToIMeetingDTO.map(meetingDetails);
        log.info("validation of can schedule ...");
        meetingValidator.canScheduleValidator(meetingServiceDTO);
        log.info("validation is done ...");

        Object body = thriftMeetingService.canScheduleMeeting(meetingServiceDTO);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meeting can be scheduled",200,data,null);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/schedule")
    public ResponseEntity<ResponseDTO> meetingSchedule(@RequestBody MeetingRequestDTO meetingDetails) throws TException {
        log.info("scheduling the meeting ...");

        IMeetingServiceDTO  meetingServiceDTO = MeetingRequestToIMeetingDTO.map(meetingDetails);
        log.info("validation of meeting schedule ...");
        meetingValidator.canScheduleValidator(meetingServiceDTO);
        log.info("validation is done ...");

        IMeetingServiceDTO body = thriftMeetingService.meetingSchedule(meetingServiceDTO);
        MeetingRequestDTO responseMeetingDetails =  IMeetingToMeetingRequest.map(body);
        Map<String, Object> data = new HashMap<>();
        data.put("body",responseMeetingDetails);
        ResponseDTO responseBody = new ResponseDTO("Meeting scheduled successfully",201,data,null);
        return ResponseEntity.ok(responseBody);

    }


    @PutMapping("/employee/update-status")
    public ResponseEntity<ResponseDTO> updateStatusMeeting(@RequestBody MeetingStatusDTO meetingStatusDTO){
        log.info("updating meeting status of a employee ...");

        log.info("validation for updating the meeting status of an employee ...");
        EmployeeMeetingStatusModel employeeMeetingStatus = meetingValidator.updateEmployeeMeetingStatusValidator(meetingStatusDTO);
        log.info("validation done ...");

        Object body = meetingService.updateEmployeeMeetingStatus(meetingStatusDTO, employeeMeetingStatus);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meeting status updated successfully",201,data,null);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<ResponseDTO> getMeetingById(@PathVariable int meetingId){
        log.info("getting meeting information by meetingId ...");

        Object body = meetingService.getMeetingById(meetingId);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meeting room detail retrieved successfully",200,data,null);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{meetingId}")
    public ResponseEntity<ResponseDTO> cancelMeetingById(@PathVariable int meetingId){
        log.info("deleting the meeting by id");

        CancelMeetingResponseDTO body = meetingService.cancelMeetingById(meetingId);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meeting canceled successfully",200,data,null);
        return ResponseEntity.ok(responseBody);

    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<ResponseDTO> getEmployeeRecords(
            @PathVariable("employeeId") int employeeId,
            @RequestParam(value = "filterType", defaultValue = "today") String filterType,
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        log.info("getting all meeting of employee by filtering");

        List<EmployeeMeetingResponseDTO> body = meetingService.getEmployeeMeeting(employeeId, filterType, startDate, endDate);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meetings retrieved successfully",200,data,null);
        return ResponseEntity.ok(responseBody);
    }

}
