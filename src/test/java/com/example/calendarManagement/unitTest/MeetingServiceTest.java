package com.example.calendarManagement.unitTest;

import com.example.calendarManagement.dto.*;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.*;
import com.example.calendarManagement.repository.EmployeeRepo;
import com.example.calendarManagement.repository.MeetingRepo;
import com.example.calendarManagement.repository.MeetingStatusRepo;
import com.example.calendarManagement.service.MeetingService;
import com.example.calendarManagement.service.ThriftMeetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class MeetingServiceTest {

    Set<EmployeeModel> employees = new HashSet<>();
    private MeetingStatusModel savedMeetingStatusModel;
    private List<MeetingModel> mockMeetings;
    private MeetingStatusModel updateMeetingStatusModel;
    private MeetingStatusDTO meetingStatusDTO;
    private MeetingStatusDTO missingMeetingStatusDTO;
    private EmployeeModel savedEmployee;
    private MeetingModel meetingModel;
    private MeetingRoomModel meetingRoom;
    private OfficeModel office;

    @InjectMocks
    private MeetingService meetingService;

    @Mock
    private MeetingStatusRepo meetingStatusRepo;

    @Mock
    private MeetingRepo meetingRepo;

    @Mock
    private EmployeeRepo employeeRepo;

    @BeforeEach
    void setup(){

        office =  new OfficeModel(1,"Headquarters","New York");

        meetingRoom = new MeetingRoomModel(1,"Conference xyz",office,true);

        meetingModel = new MeetingModel(
                1,
                "on boarding meeting",
                "check update of intern",
                meetingRoom,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                true
        );

        savedEmployee = new EmployeeModel( 1,"John Doe", "john.doe@xyz.com", office,"Engineering",
                true, 50000 );

        employees.add(savedEmployee);
        savedMeetingStatusModel = new MeetingStatusModel(1,meetingModel,true,employees);
        updateMeetingStatusModel =  new MeetingStatusModel(1,meetingModel,false,employees);
        meetingStatusDTO = new MeetingStatusDTO(1,1,false);
        missingMeetingStatusDTO = new MeetingStatusDTO(0,1,null);

        mockMeetings = Arrays.asList(new MeetingModel(1,
                "on boarding meeting",
                "check update of intern",
                meetingRoom,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                true),

                new MeetingModel(2,
                "on boarding meeting",
                "check update of intern",
                meetingRoom,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                true));

    }

    @Test
    void test_whenUpdatingMeetingStatus_givenValidInput_updateMeetingStatusSuccess(){

        Mockito.when(meetingStatusRepo.findMeetingStatusByEmployeeAndMeeting(Mockito.anyInt(),Mockito.anyInt())).
                thenReturn(Optional.of(savedMeetingStatusModel));

        Mockito.when(meetingStatusRepo.save(Mockito.any(MeetingStatusModel.class))).
                thenReturn(updateMeetingStatusModel);

        MeetingStatusDTO result = meetingService.updateStatusMeeting(meetingStatusDTO);

        assertThat(result).isNotNull();
        assertThat(result.isStatus()).isEqualTo(meetingStatusDTO.isStatus());

    }

    @Test
    public void test_whenUpdatingMeetingStatus_givenMissingInput_ThrowMissingInputException(){
        MissingFieldException thrownException = assertThrows(MissingFieldException.class,()->{
                    meetingService.updateStatusMeeting(missingMeetingStatusDTO);
                }
        );
        assertEquals("Input Field for update meeting status is missing",thrownException.getMessage());
    }

    @Test
    public void test_whenUpdatingMeetingStatus_givenInvalidInput_ThrowNotFoundException(){
        Mockito.when(meetingStatusRepo.findMeetingStatusByEmployeeAndMeeting(Mockito.anyInt(),Mockito.anyInt())).
                thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
                    meetingService.updateStatusMeeting(meetingStatusDTO);
                }
        );
        assertEquals("meeting status not found with give employeeId and meetingId",thrownException.getMessage());

    }

    @Test
    public void test_whenGettingMeetingById_whenGivenValidMeetingId_gettingMeetingSuccess(){
        Mockito.when(meetingRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(meetingModel));

        MeetingResponseDTO result = meetingService.getMeetingById(1);

        assertThat(result).isNotNull();

    }

    @Test
    public void test_whenGettingMeetingById_whenGivenInvalidMeetingId_ThrowNotFoundException(){
        Mockito.when(meetingRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
                    meetingService.getMeetingById(100);
                }
        );
        assertEquals("meeting not found with given meetingId",thrownException.getMessage());

    }

    @Test
    public void test_whenCancelMeetingById_whenGivenValidMeetingId_deleteMeetingSuccess(){
        Mockito.when(meetingRepo.findValidMeeting(Mockito.anyInt())).thenReturn(Optional.of(meetingModel));
        doNothing().when(meetingStatusRepo).updateMeetingStatusToFalse(Mockito.anyInt());
        meetingModel.setValid(false);
        Mockito.when(meetingRepo.save(meetingModel)).thenReturn(meetingModel);
        CancelMeetingResponseDTO response = meetingService.cancelMeetingById(1);

        assertThat(response).isNotNull();
    }

    @Test
    public void test_whenCancelMeetingById_whenGivenInvalidMeetingId_ThrowNotFoundException(){
        Mockito.when(meetingRepo.findValidMeeting(Mockito.anyInt())).thenReturn(Optional.empty());
        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
                    meetingService.cancelMeetingById(100);
                }
        );
        assertEquals("Meeting not found with given id "+ 100,thrownException.getMessage());

    }
    @Test
    public void test_getEmployeeMeeting_whenGivenValidInput_getEmployeeMeetingSuccess(){
        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(savedEmployee));
        Mockito.when(meetingRepo.findByEmployeeIdAndDateRange(Mockito.anyInt(),Mockito.any(),Mockito.any())).
                thenReturn(mockMeetings);

        List<EmployeeMeetingResponseDTO> result = meetingService.getEmployeeMeeting(1,"today",null,null);
        assertThat(result).isNotNull();
    }

    @Test
    public void test_getEmployeeMeeting_whenGivenInvalidInput_ThrowNotFoundException(){
        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
                    meetingService.getEmployeeMeeting(100,"today",null,null);
                }
        );
        assertEquals("Employee not found with given ID",thrownException.getMessage());
    }

    @Test
    public void test_getEmployeeMeeting_whenGivenMissingInput_ThrowMissingInputException(){
        Mockito.when(employeeRepo.findById(Mockito.anyInt())).
                thenReturn(Optional.of(savedEmployee));

        MissingFieldException thrownException = assertThrows(MissingFieldException.class,()->{
                    meetingService.getEmployeeMeeting(100,"custom_range",null,null);
                }
        );
        assertEquals("startDate and endDate is not provided",thrownException.getMessage());

    }


}
