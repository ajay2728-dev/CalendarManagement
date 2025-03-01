package com.example.calendarManagement.service;

import com.example.calendarManagement.dto.MeetingStatusDTO;
import com.example.calendarManagement.exception.ThriftServerException;
import com.example.calendarManagement.validator.MeetingValidator;
import com.example.thriftMeeting.IMeetingService;
import com.example.thriftMeeting.IMeetingServiceDTO;
import com.example.thriftMeeting.IMeetingServiceResponse;
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
public class ThriftMeetingService {
    private final IMeetingService.Client client;

    @Autowired
    private MeetingValidator meetingValidator;

    public ThriftMeetingService(){
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
            IMeetingServiceResponse response = client.canScheduleMeeting(meetingServiceDTO);

            if(response.getCode()!=200){
                throw new ThriftServerException(response.getMessage(),response.getCode());
            }

            Map<String, Object> data = new HashMap<>();
            data.put("canSchedule",true);
            return data;
        } catch (TException ex){
            throw new TException(ex.getMessage());
        }

    }


    public IMeetingServiceDTO meetingSchedule(IMeetingServiceDTO meetingServiceDTO) throws TException {
        try {
            meetingValidator.meetingScheduleValidator(meetingServiceDTO);
            IMeetingServiceResponse response = client.meetingSchedule(meetingServiceDTO);

            if(response.getCode()!=200){
                throw new ThriftServerException(response.getMessage(),response.getCode());
            }

            return response.getData();

        } catch (TException ex){
            throw new TException(ex.getMessage());
        }
    }

}
