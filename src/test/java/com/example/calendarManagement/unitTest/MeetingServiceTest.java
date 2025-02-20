package com.example.calendarManagement.unitTest;

import com.example.calendarManagement.dto.MeetingStatusDTO;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.*;
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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MeetingServiceTest {

    Set<EmployeeModel> employees = new HashSet<>();
    private MeetingStatusModel savedMeetingStatusModel;
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

        MeetingModel result = meetingService.getMeetingById(1);

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
}
