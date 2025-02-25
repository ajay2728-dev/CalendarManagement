package com.example.calendarManagement.unitTest;

import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.InvalidFieldException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.model.EmployeeMeetingStatusModel;
import com.example.calendarManagement.service.ThriftMeetingService;
import com.example.calendarManagement.validator.MeetingValidator;
import com.example.thriftMeeting.IMeetingService;
import com.example.thriftMeeting.IMeetingServiceDTO;
import org.apache.thrift.TException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ThriftMeetingServiceTest {

    private IMeetingServiceDTO meetingDTO;

    @InjectMocks
    private ThriftMeetingService thriftMeetingService;

    @InjectMocks
    private MeetingValidator meetingValidator;

    @Mock
    private EmployeeMeetingStatusModel employeeMeetingStatusModel;

    @Mock
    private IMeetingService.Client client;

    @BeforeEach
    void setup() {
        meetingDTO = mock(IMeetingServiceDTO.class);
    }

    @Test
    void testCanScheduleValidator_ShouldThrowMissingFieldException() {

        MissingFieldException thrownException = assertThrows(MissingFieldException.class, () ->
                meetingValidator.canScheduleValidator(meetingDTO));

        assertEquals("Missing employeeIDs or startTime or endTime.",thrownException.getMessage());
    }

    @Test
    void testCanScheduleValidator_ShouldThrowConstraintViolationException() {

        when(meetingDTO.getEmployeeIDs()).thenReturn(Arrays.asList(1, 2, 3, 4, 5));
        when(meetingDTO.getStartTime()).thenReturn("2025-03-01 10:30");
        when(meetingDTO.getEndTime()).thenReturn("2025-03-01 11:00");

        ConstraintViolationException thrownException = assertThrows(ConstraintViolationException.class, () ->
                meetingValidator.canScheduleValidator(meetingDTO));

        assertEquals("Number of Employee is less than 6",thrownException.getMessage());
    }

    @Test
    void testCanScheduleValidator_ShouldThrowInvalidFieldException_ForDifferentDates() {
        when(meetingDTO.getEmployeeIDs()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6));
        when(meetingDTO.getStartTime()).thenReturn("2025-03-01 11:00");
        when(meetingDTO.getEndTime()).thenReturn("2025-03-02 12:00");

        InvalidFieldException thrownException = assertThrows(InvalidFieldException.class, () ->
                meetingValidator.canScheduleValidator(meetingDTO));

        assertEquals("Start time and end time must be on the same day.",thrownException.getMessage());
    }

    @Test
    void testCanScheduleValidator_ShouldThrowInvalidFieldException_ForOutOfRangeStartTime() {
        when(meetingDTO.getEmployeeIDs()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6));
        when(meetingDTO.getStartTime()).thenReturn("2025-03-01 09:00");
        when(meetingDTO.getEndTime()).thenReturn("2025-03-01 11:00");

        InvalidFieldException thrownException = assertThrows(InvalidFieldException.class, () ->
                meetingValidator.canScheduleValidator(meetingDTO));

        assertEquals("Start time must be between 10 AM and 6 PM.",thrownException.getMessage());

    }

    @Test
    void testCanScheduleValidator_ShouldThrowInvalidFieldException_ForOutOfRangeEndTime() {
        when(meetingDTO.getEmployeeIDs()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6));
        when(meetingDTO.getStartTime()).thenReturn("2025-03-01 16:00");
        when(meetingDTO.getEndTime()).thenReturn("2025-03-01 19:00");

        InvalidFieldException thrownException = assertThrows(InvalidFieldException.class, () ->
                meetingValidator.canScheduleValidator(meetingDTO));

        assertEquals("End time must be between 10 AM and 6 PM.",thrownException.getMessage());
    }

    @Test
    void testCanScheduleValidator_ShouldThrowInvalidFieldException_ForPastMeeting() {

        when(meetingDTO.getEmployeeIDs()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6));
        when(meetingDTO.getStartTime()).thenReturn("2024-01-01 12:00");
        when(meetingDTO.getEndTime()).thenReturn("2024-01-01 12:30");

        InvalidFieldException thrownException = assertThrows(InvalidFieldException.class, () ->
                meetingValidator.canScheduleValidator(meetingDTO));

        assertEquals("Meeting cannot be scheduled in the past.",thrownException.getMessage());
    }

    @Test
    void testCanScheduleValidator_ShouldThrowInvalidFieldException_ForShortMeetingDuration() {
        when(meetingDTO.getEmployeeIDs()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6));
        when(meetingDTO.getStartTime()).thenReturn("2025-03-01 12:00");
        when(meetingDTO.getEndTime()).thenReturn("2025-03-01 12:15");

        InvalidFieldException  thrownException = assertThrows(InvalidFieldException.class, () ->
                meetingValidator.canScheduleValidator(meetingDTO));
        assertEquals("The meeting duration must be at least 30 minutes.",thrownException.getMessage());
    }

    @Test
    void testCanScheduleValidator_ShouldPassForValidMeeting() {
        when(meetingDTO.getEmployeeIDs()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6));
        when(meetingDTO.getStartTime()).thenReturn("2025-03-01 11:00");
        when(meetingDTO.getEndTime()).thenReturn("2025-03-01 12:00");

        assertDoesNotThrow(() -> meetingValidator.canScheduleValidator(meetingDTO));
    }



}
