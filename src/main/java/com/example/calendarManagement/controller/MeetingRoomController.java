package com.example.calendarManagement.controller;

import com.example.calendarManagement.dto.MeetingRoomRequestDTO;
import com.example.calendarManagement.dto.ResponseDTO;
import com.example.calendarManagement.model.MeetingRoomModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.repository.MeetingRoomRepo;
import com.example.calendarManagement.service.MeetingRoomService;
import com.example.calendarManagement.validator.MeetingRoomValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/meeting-room")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    @Autowired
    private MeetingRoomValidator meetingRoomValidator;

    @PostMapping
    public ResponseEntity<ResponseDTO> addMeetingRoom(@RequestBody MeetingRoomRequestDTO meetingRoom) {
        log.info("adding meeting room controller ...");

        log.info("validation of adding meeting room ...");
        OfficeModel office = meetingRoomValidator.ValidatorAddMeetingRoom(meetingRoom);
        log.info("validation is done ...");

        MeetingRoomModel body = meetingRoomService.addMeetingRoom(meetingRoom,office);
        log.info("meeting room added ...");

        Map<String, Object> data = new HashMap<>();
        data.put("roomId",body.getRoomId());
        ResponseDTO response = new ResponseDTO("Meeting room added successfully",201,data,null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{meetingRoomId}")
    public ResponseEntity<ResponseDTO> getMeetingRoomById(@PathVariable int meetingRoomId ){
        log.info("getting meeting room by id controller ...");

        MeetingRoomModel body = meetingRoomService.getMeetingRoomById(meetingRoomId);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO response = new ResponseDTO("Meeting room detail retrieved successfully",200,data,null);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update-status/enable/{meetingRoomId}")
    public ResponseEntity<ResponseDTO> updateMeetingRoomStatusToEnable(@PathVariable int meetingRoomId ){
        log.info("updating meeting room status to enable controller ...");

        MeetingRoomModel meetingRoom = meetingRoomValidator.ValidatorUpdateMeetingRoomStatusToEnable(meetingRoomId);
        MeetingRoomModel body = meetingRoomService.updateMeetingRoomStatusToEnable(meetingRoom);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meeting room status updated",201,data,null);

        return ResponseEntity.ok(responseBody);

    }

    @PutMapping("/update-status/disable/{meetingRoomId}")
    public ResponseEntity<ResponseDTO> updateMeetingRoomStatusToDisable(@PathVariable int meetingRoomId){
        log.info("updating meeting room status");

        MeetingRoomModel meetingRoom = meetingRoomValidator.ValidatorUpdateMeetingRoomStatusToDisable(meetingRoomId);
        MeetingRoomModel body = meetingRoomService.updateStatusMeetingRoom(meetingRoom);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body);
        ResponseDTO responseBody = new ResponseDTO("Meeting room status updated",201,data,null);

        return ResponseEntity.ok(responseBody);

    }

}
