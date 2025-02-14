package com.example.calendarManagement.controller;

import com.example.calendarManagement.dto.MeetingRoomRequestDTO;
import com.example.calendarManagement.dto.ResponseDTO;
import com.example.calendarManagement.model.MeetingRoomModel;
import com.example.calendarManagement.repository.MeetingRoomRepo;
import com.example.calendarManagement.service.MeetingRoomService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/meetingroom")
public class MeetingRoomController {

    @Autowired
    MeetingRoomService meetingRoomService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addMeetingRoom(@RequestBody MeetingRoomRequestDTO meetingRoom) {
       log.info("adding meeting room");

        MeetingRoomModel body = meetingRoomService.addMeetingRoom(meetingRoom);
        Map<String, Object> data = new HashMap<>();
        data.put("body",body.getRoomId());
        ResponseDTO response = new ResponseDTO("Meeting room added successfully",201,data,null);
        return ResponseEntity.ok(response);
    }

}
