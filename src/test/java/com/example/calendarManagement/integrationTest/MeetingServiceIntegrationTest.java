package com.example.calendarManagement.integrationTest;


import com.example.calendarManagement.dto.CancelMeetingResponseDTO;
import com.example.calendarManagement.dto.MeetingStatusDTO;
import com.example.calendarManagement.dto.ResponseDTO;
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
import java.util.Objects;

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
        requestDto = new MeetingStatusDTO(2, 2, true);
    }

    @Test
    void testUpdateMeetingStatus(){
        String url = baseUrl+"/employee/updateStatus";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MeetingStatusDTO> requestEntity = new HttpEntity<>(requestDto, headers);

        ResponseEntity<MeetingStatusDTO> response = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, MeetingStatusDTO.class);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testGettingMeetingById(){
        String url = baseUrl + "/" + 1;
        ResponseDTO response = restTemplate.getForObject(url,ResponseDTO.class);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Meeting room detail retrieved successfully");
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getError()).isEqualTo(null);
    }

    @Test
    void testCancelMeetingById(){

        String url = baseUrl + "/" + 1;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CancelMeetingResponseDTO> response = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, CancelMeetingResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void testGettingEmployeeMeeting(){
        String url = baseUrl + "/employee/" + 1 + "?filterType=today";
        ResponseDTO response = restTemplate.getForObject(url,ResponseDTO.class);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Meetings retrieved successfully");
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getError()).isEqualTo(null);

    }

}
