package com.example.calendarManagement.validator;

import com.example.calendarManagement.dto.MeetingRoomRequestDTO;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.MeetingRoomModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.repository.MeetingRoomRepo;
import com.example.calendarManagement.repository.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MeetingRoomValidator {

    @Autowired
    private OfficeRepo officeRepo;

    @Autowired
    private MeetingRoomRepo meetingRoomRepo;

    public OfficeModel validatorAddMeetingRoom(MeetingRoomRequestDTO meetingRoom){

        // check missing input
        if(meetingRoom.getRoomName()==null || meetingRoom.getOfficeId()==0){
            throw new MissingFieldException("Missing Required Input");
        }

        // search valid office
        Optional<OfficeModel> office = officeRepo.findById(meetingRoom.getOfficeId());
        if(!office.isPresent()){
            throw new NotFoundException("provided office not found");
        }

        if(meetingRoomRepo.findByRoomName(meetingRoom.getRoomName()).isPresent()){
            throw new ConstraintViolationException("Give Different Room Name");
        }

        // no. of office room
        int countMeetingRoom = meetingRoomRepo.countByOffice(office.get());

        if(countMeetingRoom>=10){
            throw new ConstraintViolationException("Office Already have 10 meeting room");
        }
        return office.get();
    }

    public MeetingRoomModel validatorUpdateMeetingRoomStatusToEnable(int meetingRoomId){
        //check valid room id
        Optional<MeetingRoomModel> exitingRoomOpt = meetingRoomRepo.findById(meetingRoomId);
        if(!exitingRoomOpt.isPresent()){
            throw new NotFoundException("Room Not Found with given Id");
        }

        // already enable
        if(exitingRoomOpt.get().isEnable()){
            throw new ConstraintViolationException("Meeting Room already Enable");
        }

        return exitingRoomOpt.get();
    }

    public MeetingRoomModel validatorUpdateMeetingRoomStatusToDisable(int meetingRoomId){
        //check valid room id
        Optional<MeetingRoomModel> exitingRoomOpt = meetingRoomRepo.findById(meetingRoomId);
        if(!exitingRoomOpt.isPresent()){
            throw new NotFoundException("Room Not Found with given Id");
        }

        // already disable
        if(!exitingRoomOpt.get().isEnable()){
            throw new ConstraintViolationException("Meeting Room already Disable");
        }

        return exitingRoomOpt.get();
    }
}
