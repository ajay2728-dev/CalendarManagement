package com.example.calendarManagement.service;

import com.example.thriftMeeting.IMeetingService;
import com.example.thriftMeeting.IMeetingServiceDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MeetingService {
    private final IMeetingService.Client client;

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
            boolean response = client.canScheduleMeeting(meetingServiceDTO);
            Map<String, Object> data = new HashMap<>();
            data.put("canSchedule",response);
            return data;
        } catch ( RuntimeException | TException ex){
            throw new RuntimeException(ex);
        }

    }

}
