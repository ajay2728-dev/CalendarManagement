package com.example.calendarManagement.integrationTests;


import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.dto.ResponseDTO;
import com.example.calendarManagement.repository.EmployeeRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeRepo employeeRepo;

    @LocalServerPort
    private int port;

    private static RestTemplate restTemplate;
    private String baseUrl;
    private EmployeeRequestDTO inputEmployee;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    void setup(){
        baseUrl = "http://localhost:" + port + "/api/employee";

        inputEmployee=new EmployeeRequestDTO(0, "John Doe", "New York",
                "john.doe@xyz.com", true, 50000, "Engineering");
    }

    @Test
    void testAddEmployee(){
        ResponseDTO response = restTemplate.postForObject(baseUrl,inputEmployee,ResponseDTO.class);

        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Employee added successfully");
        assertThat(response.getCode()).isEqualTo(201);
        assertThat(response.getError()).isEqualTo(null);
        assertThat(employeeRepo.findAll().size()).isEqualTo(1);

    }

//    @Test
//    void testGetAllEmployee

}

