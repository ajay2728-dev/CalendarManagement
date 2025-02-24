package com.example.calendarManagement.service;

import com.example.calendarManagement.dto.MeetingStatusDTO;
import com.example.calendarManagement.exception.ThriftServerException;
import com.example.calendarManagement.validator.MeetingValidator;
import com.example.thriftMeeting.IMeetingService;
import com.example.thriftMeeting.IMeetingServiceDTO;
import com.example.thriftMeeting.IMeetingServiceResponse;
import com.example.thriftMeeting.MeetingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ThriftMeetingService {
    private final IMeetingService.Client client;
    private final ExecutorService executor = Executors.newCachedThreadPool();

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

            log.info("making an can schedule call to thrift server ...");
            boolean response = client.canScheduleMeeting(meetingServiceDTO);
            log.info("response is come from thrift server ...");

            Map<String, Object> data = new HashMap<>();
            data.put("canSchedule",response);
            return data;

        } catch (MeetingException ex){
            throw new MeetingException(ex.getMessage(), ex.errorCode);
        }

    }

    public IMeetingServiceDTO meetingSchedule(IMeetingServiceDTO meetingServiceDTO) throws TException {
        try {
            IMeetingServiceDTO response = client.meetingSchedule(meetingServiceDTO);
            return response;
        } catch (MeetingException ex){
            throw new MeetingException(ex.getMessage(), ex.errorCode);
        }
    }


}
