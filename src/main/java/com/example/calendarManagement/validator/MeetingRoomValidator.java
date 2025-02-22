package com.example.calendarManagement.validator;

import com.example.calendarManagement.dto.MeetingResponseDTO;
import com.example.calendarManagement.dto.MeetingRoomRequestDTO;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.MeetingModel;
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

    public OfficeModel ValidatorAddMeetingRoom(MeetingRoomRequestDTO meetingRoom){

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
        return office.get();
    }

    public MeetingRoomModel ValidatorUpdateMeetingRoomStatusToEnable(int meetingRoomId){
        //check valid room id
        Optional<MeetingRoomModel> exitingRoomOpt = meetingRoomRepo.findById(meetingRoomId);
        if(!exitingRoomOpt.isPresent()){
            throw new NotFoundException("Room Not Found with given meetingId");
        }

        // already enable
        if(exitingRoomOpt.get().getIsEnable()){
            throw new ConstraintViolationException("Meeting Room already Enable");
        }

        return exitingRoomOpt.get();
    }

    public MeetingRoomModel ValidatorUpdateMeetingRoomStatusToDisable(int meetingRoomId){
        //check valid room id
        Optional<MeetingRoomModel> exitingRoomOpt = meetingRoomRepo.findById(meetingRoomId);
        if(!exitingRoomOpt.isPresent()){
            throw new NotFoundException("Room Not Found with given meetingId");
        }

        // already disable
        if(!exitingRoomOpt.get().getIsEnable()){
            throw new ConstraintViolationException("Meeting Room already Disable");
        }

        return exitingRoomOpt.get();
    }
}
