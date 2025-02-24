package com.example.calendarManagement.service;

import com.example.calendarManagement.dto.MeetingRoomRequestDTO;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.MeetingRoomModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.repository.MeetingRoomRepo;
import com.example.calendarManagement.repository.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetingRoomService {

    @Autowired
    private OfficeRepo officeRepo;

    @Autowired
    private MeetingRoomRepo meetingRoomRepo;

    public MeetingRoomModel addMeetingRoom(MeetingRoomRequestDTO meetingRoom, OfficeModel office)  {

        MeetingRoomModel newMeetingRoom = new MeetingRoomModel( meetingRoom.getRoomName(), office, meetingRoom.isEnable() );
        // add meetingRoom;
        MeetingRoomModel saveMeetingRoom = meetingRoomRepo.save(newMeetingRoom);
        return saveMeetingRoom;
    }

    public MeetingRoomModel updateMeetingRoomStatusToEnable(MeetingRoomModel exitingRoom) {
        //update status of meeting room
        exitingRoom.setEnable(true);
        MeetingRoomModel updateMeetingRoom = meetingRoomRepo.save(exitingRoom);
        return updateMeetingRoom;

    }

    public MeetingRoomModel updateMeetingRoomStatusToDisable(MeetingRoomModel exitingRoom) {
        //update status of meeting room
        exitingRoom.setEnable(false);
        MeetingRoomModel updatedMeetingRoom = meetingRoomRepo.save(exitingRoom);
        return updatedMeetingRoom;

    }

    public MeetingRoomModel getMeetingRoomById(int meetingRoomId) {
        // check valid meeting room
        Optional<MeetingRoomModel> meetingRoomOpt = meetingRoomRepo.findById(meetingRoomId);
        if(!meetingRoomOpt.isPresent()){
            throw new NotFoundException("Meeting room not found");
        }
        MeetingRoomModel existingRoom = meetingRoomOpt.get();
        return existingRoom;
    }
}
