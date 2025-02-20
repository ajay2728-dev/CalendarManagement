package com.example.calendarManagement.controller;

import com.example.calendarManagement.dto.MeetingRequestDTO;
import com.example.calendarManagement.dto.MeetingStatusDTO;
import com.example.calendarManagement.dto.ResponseDTO;
import com.example.calendarManagement.objectMapper.IMeetingToMeetingRequest;
import com.example.calendarManagement.objectMapper.MeetingRequestToIMeetingDTO;
import com.example.calendarManagement.service.MeetingService;
import com.example.calendarManagement.service.ThriftMeetingService;
import com.example.calendarManagement.validator.MeetingValidator;
import com.example.thriftMeeting.IMeetingService;
import com.example.thriftMeeting.IMeetingServiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/schedule")
    public ResponseEntity<ResponseDTO> canScheduleMeeting(@RequestBody MeetingRequestDTO meetingDetails) throws TException {
        log.info("can schedule meeting");

        IMeetingServiceDTO  meetingServiceDTO = MeetingRequestToIMeetingDTO.map(meetingDetails);
        Object body = thriftMeetingService.canScheduleMeeting(meetingServiceDTO);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meeting can be scheduled",200,data,null);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> meetingSchedule(@RequestBody MeetingRequestDTO meetingDetails) throws TException {
        log.info("scheduling the meeting ...");

        IMeetingServiceDTO  meetingServiceDTO = MeetingRequestToIMeetingDTO.map(meetingDetails);
        IMeetingServiceDTO body = thriftMeetingService.meetingSchedule(meetingServiceDTO);
        MeetingRequestDTO responseMeetingDetails =  IMeetingToMeetingRequest.map(body);
        Map<String, Object> data = new HashMap<>();
        data.put("body",responseMeetingDetails);
        ResponseDTO responseBody = new ResponseDTO("Meeting scheduled successfully",201,data,null);
        return ResponseEntity.ok(responseBody);

    }

    @PutMapping("/update-status")
    public ResponseEntity<ResponseDTO> updateStatusMeeting(@RequestBody MeetingStatusDTO meetingStatusDTO){
        log.info("updating meeting status of a employee");

        Object body = meetingService.updateStatusMeeting(meetingStatusDTO);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meeting status updated successfully",201,data,null);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<ResponseDTO> getMeetingById(@PathVariable int meetingId){
        log.info("getting meeting information by meetingId");

        Object body = meetingService.getMeetingById(meetingId);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meeting room detail retrieved successfully",200,data,null);
        return ResponseEntity.ok(responseBody);
    }


}
