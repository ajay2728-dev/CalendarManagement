package com.example.calendarManagement.integrationTest;


import com.example.calendarManagement.dto.ResponseDTO;
import com.example.thriftMeeting.IMeetingServiceDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ThriftMeetingServiceIntegrationTest {

    @LocalServerPort
    private int port;

    private IMeetingServiceDTO meetingServiceDTO;
    private static RestTemplate restTemplate;
    private String baseUrl;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    void setup(){
        baseUrl = "http://localhost:" + port + "/api/meeting";
        meetingServiceDTO = new IMeetingServiceDTO();
        meetingServiceDTO.setStartTime("2025-02-26 13:00");
        meetingServiceDTO.setEndTime("2025-02-26 14:00");
        meetingServiceDTO.setEmployeeIDs(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));

    }

    @Test
    void testCanScheduleMeeting(){
        String url = baseUrl+"/can-schedule";
        ResponseDTO response = restTemplate.postForObject(url,meetingServiceDTO,ResponseDTO.class);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Meeting can be scheduled");
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getError()).isEqualTo(null);

    }

    @Test
    void testMeetingSchedule(){
        String url = baseUrl+"/schedule";
        meetingServiceDTO.setDescription("on boarding meeting");
        meetingServiceDTO.setAgenda("Check update of intern work");
        ResponseDTO response = restTemplate.postForObject(url,meetingServiceDTO, ResponseDTO.class);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Meeting scheduled successfully");
        assertThat(response.getCode()).isEqualTo(201);
        assertThat(response.getError()).isEqualTo(null);

    }
}
