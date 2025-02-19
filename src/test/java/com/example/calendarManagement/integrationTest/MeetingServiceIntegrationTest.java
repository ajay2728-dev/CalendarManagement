package com.example.calendarManagement.integrationTest;


import com.example.calendarManagement.dto.MeetingStatusDTO;
import com.example.thriftMeeting.IMeetingServiceDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeetingServiceIntegrationTest {

    @LocalServerPort
    private int port;

    private static RestTemplate restTemplate;
    private String baseUrl;
    private MeetingStatusDTO requestDto;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    void setup(){
        baseUrl = "http://localhost:" + port + "/api/meeting";
        requestDto = new MeetingStatusDTO(4, 1, false);
    }

    @Test
    void testUpdateMeetingStatus(){
        String url = baseUrl+"/update-status";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MeetingStatusDTO> requestEntity = new HttpEntity<>(requestDto, headers);

        ResponseEntity<MeetingStatusDTO> response = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, MeetingStatusDTO.class);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);


    }
}
