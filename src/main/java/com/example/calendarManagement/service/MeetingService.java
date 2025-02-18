package com.example.calendarManagement.service;

import com.example.calendarManagement.model.MeetingModel;
import com.example.calendarManagement.validator.MeetingValidator;
import com.example.thriftMeeting.IMeetingService;
import com.example.thriftMeeting.IMeetingServiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MeetingService {
    private final IMeetingService.Client client;

    @Autowired
    private MeetingValidator meetingValidator;

    public MeetingService(){
        try {
            log.info("meeting thrift ...");
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            client = new IMeetingService.Client(protocol);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing Thrift client", e);
        }
    }


    public Object canScheduleMeeting(IMeetingServiceDTO meetingServiceDTO) throws TException {
        try {
            meetingValidator.canScheduleValidator(meetingServiceDTO);
            boolean response = client.canScheduleMeeting(meetingServiceDTO);
            Map<String, Object> data = new HashMap<>();
            data.put("canSchedule",response);
            return data;
        } catch (TException ex){
            throw new TException(ex.getMessage());
        }

    }


    public Object meetingSchedule(IMeetingServiceDTO meetingServiceDTO) throws TException {
        try {
            meetingValidator.meetingScheduleValidator(meetingServiceDTO);
            IMeetingServiceDTO response = client.meetingSchedule(meetingServiceDTO);
            return response;

        } catch (TException ex){
            throw new TException(ex.getMessage());
        }
    }
}
