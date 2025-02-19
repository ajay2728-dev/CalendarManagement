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
    private MeetingRoomRequestDTO saveMeetingRoom;
    private MeetingRoomRequestDTO updatedMeetingRoom;
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
        saveMeetingRoom = new MeetingRoomRequestDTO(1,"Alpha Conference", 1, true);
        updatedMeetingRoom = new MeetingRoomRequestDTO(1,"Alpha Conference", 1, false);
    }

    @Test
    void test_addMeetingRoom(){
        ResponseDTO response = restTemplate.postForObject(baseUrl,inputMeetingRoom,ResponseDTO.class);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Meeting room added successfully");
        assertThat(response.getCode()).isEqualTo(201);
        assertThat(response.getError()).isEqualTo(null);
    }

    @Test
    void test_updateMeetingRoomStatus(){
        String url = baseUrl + "/update-status";
        inputMeetingRoom.setEnable(false);
        restTemplate.put(url,updatedMeetingRoom);

        String getMeetingRoomByIdUrl = baseUrl+"/"+1;
        ResponseDTO response = restTemplate.getForObject(getMeetingRoomByIdUrl,ResponseDTO.class);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Meeting room detail retrieved successfully");
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getError()).isEqualTo(null);

    }

    @Test
    void test_gettingMeetingRoomById(){
        String url = baseUrl+"/"+1;

        ResponseDTO response = restTemplate.getForObject(url,ResponseDTO.class);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Meeting room detail retrieved successfully");
        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getError()).isEqualTo(null);
    }

}
