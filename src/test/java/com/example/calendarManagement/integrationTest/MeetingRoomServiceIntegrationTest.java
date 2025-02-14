package com.example.calendarManagement.integrationTest;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.dto.MeetingRoomRequestDTO;
import com.example.calendarManagement.dto.ResponseDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeetingRoomServiceIntegrationTest {

    @LocalServerPort
    private int port;

    private MeetingRoomRequestDTO inputMeetingRoom;
    private static RestTemplate restTemplate;
    private String baseUrl;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    void setup(){
        baseUrl = "http://localhost:" + port + "/api/meetingroom";
        inputMeetingRoom = new MeetingRoomRequestDTO(0, "Alpha Conference", 1, true);
    }

    @Test
    void test_addMeetingRoom(){
        ResponseDTO response = restTemplate.postForObject(baseUrl,inputMeetingRoom,ResponseDTO.class);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Meeting room added successfully");
        assertThat(response.getCode()).isEqualTo(201);
        assertThat(response.getError()).isEqualTo(null);
    }
}
