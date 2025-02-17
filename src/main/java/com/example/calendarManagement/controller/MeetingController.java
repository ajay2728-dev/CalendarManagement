package com.example.calendarManagement.controller;

import com.example.calendarManagement.dto.ResponseDTO;
import com.example.calendarManagement.service.MeetingService;
import com.example.thriftMeeting.IMeetingServiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/meeting")
public class MeetingController {

    @Autowired
    MeetingService meetingService;

    @PostMapping("/schedule")
    public ResponseEntity<ResponseDTO> canScheduleMeeting(@RequestBody IMeetingServiceDTO meetingServiceDTO) throws TException {
        log.info("can schedule meeting");
//        Object validationResult = meetingService.validateCanSchedule(meetingServiceDTO);
//
//
//        if(validationResult.){
//
//        }

        Object body = meetingService.canScheduleMeeting(meetingServiceDTO);

        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meeting can be scheduled",200,data,null);
        return ResponseEntity.ok(responseBody);
    }
}
