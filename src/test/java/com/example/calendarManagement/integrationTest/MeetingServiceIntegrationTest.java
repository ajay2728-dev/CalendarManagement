package com.example.calendarManagement.integrationTest;


import com.example.thriftMeeting.IMeetingServiceDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeetingServiceIntegrationTest {

    @LocalServerPort
    private int port;

    private static RestTemplate restTemplate;
    private String baseUrl;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    void setup(){
        baseUrl = "http://localhost:" + port + "/api/meeting";
    }

    @Test
    void testUpdateMeetingStatus(){
        String url = baseUrl+"/update-status";



    }
}
