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

    public MeetingRoomModel addMeetingRoom(MeetingRoomRequestDTO meetingRoom)  {
        // check missing input

        if(meetingRoom.getRoomName()==null || meetingRoom.getOfficeId()==0){
            throw new MissingFieldException("Missing Required Input");
        }

        // search valid office
        Optional<OfficeModel> office = officeRepo.findById(meetingRoom.getOfficeId());
        if(!office.isPresent()){
            throw new NotFoundException("provided office not found");
        }

        // no. of office room
        int countMeetingRoom = meetingRoomRepo.countByOffice(office.get());

        if(countMeetingRoom>=10){
            throw new ConstraintViolationException("Office Already have 10 meeting room");
        }

        int newMeetingRoomId = meetingRoomRepo.findAll().size()+1;
        MeetingRoomModel newMeetingRoom = new MeetingRoomModel( newMeetingRoomId,
                                                                meetingRoom.getRoomName(),
                                                                office.get(), meetingRoom.isEnable() );

        // add meetingRoom;
        MeetingRoomModel saveMeetingRoom = meetingRoomRepo.save(newMeetingRoom);
        return saveMeetingRoom;
    }

    public MeetingRoomModel updateStatusMeetingRoom(MeetingRoomRequestDTO meetingRoom) {
        // check missing attribute
        if(meetingRoom.getRoomId()==0){
            throw new MissingFieldException("Missing Required Input");
        }

        //check valid room id
        Optional<MeetingRoomModel> exitingRoomOpt = meetingRoomRepo.findById(meetingRoom.getRoomId());
        if(!exitingRoomOpt.isPresent()){
            throw new NotFoundException("Room Not Found");
        }

        MeetingRoomModel exitingRoom = exitingRoomOpt.get();
        exitingRoom.setEnable(meetingRoom.isEnable());

        //update status of meeting room
        MeetingRoomModel updateMeetingRoom = meetingRoomRepo.save(exitingRoom);
        return updateMeetingRoom;
    }

    public MeetingRoomModel getMeetingRoomId(int meetingRoomId) {
        // check valid meeting room
        Optional<MeetingRoomModel> meetingRoomOpt = meetingRoomRepo.findById(meetingRoomId);
        if(!meetingRoomOpt.isPresent()){
            throw new NotFoundException("Meeting room not found");
        }

        MeetingRoomModel exitingRoom = meetingRoomOpt.get();
        return exitingRoom;
    }
}
