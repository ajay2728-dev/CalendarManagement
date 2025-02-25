package com.example.calendarManagement.unitTest;

import com.example.calendarManagement.dto.*;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.InvalidFieldException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.*;
import com.example.calendarManagement.objectMapper.MeetingModelTOMeetingResponseDTO;
import com.example.calendarManagement.repository.EmployeeRepo;
import com.example.calendarManagement.repository.MeetingRepo;
import com.example.calendarManagement.repository.EmployeeMeetingStatusRepo;
import com.example.calendarManagement.service.MeetingService;
import com.example.calendarManagement.validator.MeetingValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MeetingServiceTest {

    Set<EmployeeModel> employees = new HashSet<>();
    private EmployeeMeetingStatusModel savedEmployeeMeetingStatusModel;
    private List<MeetingModel> mockMeetings;
    private EmployeeMeetingStatusModel updateEmployeeMeetingStatusModel;
    private MeetingStatusDTO meetingStatusDTO;
    private MeetingStatusDTO missingMeetingStatusDTO;
    private EmployeeModel savedEmployee;
    private MeetingModel meetingModel;
    private MeetingRoomModel meetingRoom;
    private OfficeModel office;
    private MeetingResponseDTO meetingResponseDTO;
    private final int employeeId = 1;
    private final LocalDateTime now = LocalDateTime.now();

    @InjectMocks
    private MeetingService meetingService;

    @InjectMocks
    private MeetingValidator meetingValidator;

    @Mock
    private EmployeeMeetingStatusRepo meetingStatusRepo;

    @Mock
    private MeetingRepo meetingRepo;

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private MeetingModelTOMeetingResponseDTO mapper;

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
        savedEmployeeMeetingStatusModel = new EmployeeMeetingStatusModel(meetingModel,true,savedEmployee);
        updateEmployeeMeetingStatusModel =  new EmployeeMeetingStatusModel(meetingModel,false,savedEmployee);
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

        meetingResponseDTO = new MeetingResponseDTO();

    }

    @Test
    void test_whenUpdatingMeetingStatus_givenValidInput_updateMeetingStatusSuccess(){

        when(meetingStatusRepo.save(Mockito.any(EmployeeMeetingStatusModel.class))).
                thenReturn(updateEmployeeMeetingStatusModel);

        MeetingStatusDTO result = meetingService.updateEmployeeMeetingStatus(meetingStatusDTO,updateEmployeeMeetingStatusModel);

        assertThat(result).isNotNull();
        assertThat(result.getMeetingStatus()).isEqualTo(meetingStatusDTO.getMeetingStatus());

    }

    @Test
    void testUpdateEmployeeMeetingStatusValidator_ShouldThrowMissingFieldException() {

       MissingFieldException thrownException = assertThrows(MissingFieldException.class, () ->
               meetingValidator.updateEmployeeMeetingStatusValidator(missingMeetingStatusDTO));

        assertEquals("Input Field for update meeting status is missing",thrownException.getMessage());
    }

    @Test
    void testUpdateEmployeeMeetingStatusValidator_ShouldThrowNotFoundException() {
        when(meetingStatusRepo.findMeetingStatusByEmployeeAndMeeting(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class, () ->
                meetingValidator.updateEmployeeMeetingStatusValidator(meetingStatusDTO));

        assertEquals("meeting status not found with give employeeId and meetingId",thrownException.getMessage());
    }

    @Test
    void testUpdateEmployeeMeetingStatusValidator_ShouldThrowConstraintViolationException() {

        when(meetingStatusRepo.findMeetingStatusByEmployeeAndMeeting(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(Optional.of(savedEmployeeMeetingStatusModel));

        meetingStatusDTO.setMeetingStatus(true);

        ConstraintViolationException thrownException = assertThrows(ConstraintViolationException.class, () ->
                meetingValidator.updateEmployeeMeetingStatusValidator(meetingStatusDTO));

        assertEquals("Employee already Accepted the meeting.",thrownException.getMessage());
    }

    @Test
    public void test_whenGettingMeetingById_whenGivenValidMeetingId_gettingMeetingSuccess(){
        when(meetingRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(meetingModel));
        when(mapper.map(meetingModel)).thenReturn(meetingResponseDTO);

        MeetingResponseDTO result = meetingService.getMeetingById(1);

        assertThat(result).isNotNull();

    }

    @Test
    public void test_whenGettingMeetingById_whenGivenInvalidMeetingId_ThrowNotFoundException(){
        when(meetingRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
                    meetingService.getMeetingById(100);
                }
        );
        assertEquals("meeting not found with given meetingId",thrownException.getMessage());

    }

    @Test
    void testGetMeetingById_ShouldThrowInvalidFieldException() {
        int meetingId = 101;
        MeetingModel meetingModel = new MeetingModel();
        meetingModel.setIsValid(false);

        when(meetingRepo.findById(meetingId)).thenReturn(Optional.of(meetingModel));

        InvalidFieldException thrownException = assertThrows(InvalidFieldException.class, () ->
                meetingService.getMeetingById(meetingId));
        assertEquals("Given Meeting is Cancel.",thrownException.getMessage());
    }

    @Test
    public void test_whenCancelMeetingById_whenGivenValidMeetingId_deleteMeetingSuccess(){

        int meetingId = 101;
        MeetingModel meetingModel = new MeetingModel();
        meetingModel.setIsValid(true);

        when(meetingRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(meetingModel));
        CancelMeetingResponseDTO response = meetingService.cancelMeetingById(meetingId);
        assertNotNull(response);
        assertEquals(meetingId, response.getMeetingId());
        assertThat(response.getStatus()).isEqualTo(false);
    }

    @Test
    public void test_whenCancelMeetingById_whenGivenInvalidMeetingId_ThrowNotFoundException(){
        when(meetingRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
                    meetingService.cancelMeetingById(100);
                }
        );
        assertEquals("Meeting not found with given id "+ 100,thrownException.getMessage());

    }

    @Test
    void testCancelMeetingById_ShouldThrowInvalidFieldException() {
        int meetingId = 101;
        MeetingModel meetingModel = new MeetingModel();
        meetingModel.setIsValid(false);

        when(meetingRepo.findById(meetingId)).thenReturn(Optional.of(meetingModel));

        InvalidFieldException thrownException = assertThrows(InvalidFieldException.class, () ->
                meetingService.cancelMeetingById(meetingId));

        assertEquals("Given Meeting is already Cancel.",thrownException.getMessage());
    }
    @Test
    public void test_getEmployeeMeeting_whenGivenValidInput_getEmployeeMeetingSuccess(){
        when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(savedEmployee));
        when(meetingRepo.findByEmployeeIdAndDateRange(Mockito.anyInt(),Mockito.any(),Mockito.any())).
                thenReturn(mockMeetings);

        List<EmployeeMeetingResponseDTO> result = meetingService.getEmployeeMeeting(1,"today",null,null);
        assertThat(result).isNotNull();
    }

    @Test
    public void test_getEmployeeMeeting_whenGivenInvalidInput_ThrowNotFoundException(){
        when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
                    meetingService.getEmployeeMeeting(100,"today",null,null);
                }
        );
        assertEquals("Employee not found with given ID",thrownException.getMessage());
    }

    @Test
    public void test_getEmployeeMeeting_whenGivenMissingInput_ThrowMissingInputException(){
        when(employeeRepo.findById(Mockito.anyInt())).
                thenReturn(Optional.of(savedEmployee));

        MissingFieldException thrownException = assertThrows(MissingFieldException.class,()->{
                    meetingService.getEmployeeMeeting(100,"custom_range",null,null);
                }
        );
        assertEquals("startDate and endDate is not provided",thrownException.getMessage());

    }

    @ParameterizedTest
    @CsvSource({
            "current_week, MONDAY, 6",
            "last_week, MONDAY, 6"
    })
    void testFilterByWeek(String filterType, java.time.DayOfWeek startDay, long plusDays) {
        LocalDateTime startOfWeek = now.with(startDay);
        LocalDateTime endOfWeek = startOfWeek.plusDays(plusDays);

        when(employeeRepo.findById(Mockito.anyInt())).
                thenReturn(Optional.of(savedEmployee));

        when(meetingRepo.findByEmployeeIdAndDateRange(Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.emptyList());

        List<EmployeeMeetingResponseDTO> meetings = meetingService.getEmployeeMeeting(employeeId, filterType, startOfWeek, endOfWeek);

        assertNotNull(meetings);
    }

    @Test
    void testFilterByToday() {
        LocalDateTime todayStart = now.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime todayEnd = now.withHour(23).withMinute(59).withSecond(59);

        when(employeeRepo.findById(Mockito.anyInt())).
                thenReturn(Optional.of(savedEmployee));
        when(meetingRepo.findByEmployeeIdAndDateRange(Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.emptyList());

        List<EmployeeMeetingResponseDTO> meetings = meetingService.getEmployeeMeeting(employeeId, "random", null, null);

        assertNotNull(meetings);
    }

    @Test
    void testFilterByCustomRange_Success() {
        LocalDateTime startDate = now.minusDays(10);
        LocalDateTime endDate = now.minusDays(5);

        when(employeeRepo.findById(Mockito.anyInt())).
                thenReturn(Optional.of(savedEmployee));
        when(meetingRepo.findByEmployeeIdAndDateRange(Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.emptyList());

        List<EmployeeMeetingResponseDTO> meetings = meetingService.getEmployeeMeeting(employeeId,"custom_range", startDate, endDate);

        assertNotNull(meetings);
    }

    @Test
    void testFilterByCustomRange_MissingDates() {
        when(employeeRepo.findById(Mockito.anyInt())).
                thenReturn(Optional.of(savedEmployee));
        MissingFieldException exception = assertThrows(MissingFieldException.class, () ->
                meetingService.getEmployeeMeeting(employeeId, "custom_range", null, null));

        assertEquals("startDate and endDate is not provided", exception.getMessage());
    }


}
