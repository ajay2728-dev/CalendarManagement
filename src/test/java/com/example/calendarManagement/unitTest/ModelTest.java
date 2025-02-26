package com.example.calendarManagement.unitTest;

import com.example.calendarManagement.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ModelTest {
    @Test
    void testMeetingModelConstructorAndGetters() {
        // Arrange: Create test data
        int meetingId = 1;
        String description = "Team Sync";
        String agenda = "Discuss project updates";
        MeetingRoomModel room = new MeetingRoomModel(); // Assuming default constructor
        LocalDateTime startTime = LocalDateTime.of(2025, 2, 26, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 2, 26, 11, 0);
        Boolean isValid = true;

        // Act: Create an instance using the constructor
        MeetingModel meeting = new MeetingModel(meetingId, description, agenda, room, startTime, endTime, isValid);

        // Assert: Verify the values
        assertEquals(meetingId, meeting.getMeetingId());
        assertEquals(description, meeting.getDescription());
        assertEquals(agenda, meeting.getAgenda());
        assertEquals(room, meeting.getMeetingRoom());
        assertEquals(startTime, meeting.getStartTime());
        assertEquals(endTime, meeting.getEndTime());
        assertEquals(isValid, meeting.getIsValid());
    }

    @Test
    void testSettersMeeting() {
        // Arrange: Create an empty meeting instance
        MeetingModel meeting = new MeetingModel();

        // Act: Set values
        meeting.setMeetingId(2);
        meeting.setDescription("Client Call");
        meeting.setAgenda("Discuss contract terms");
        MeetingRoomModel room = new MeetingRoomModel();
        meeting.setMeetingRoom(room);
        meeting.setStartTime(LocalDateTime.of(2025, 3, 1, 14, 0));
        meeting.setEndTime(LocalDateTime.of(2025, 3, 1, 15, 0));
        meeting.setIsValid(false);
        meeting.setStatuses(Collections.emptyList());

        // Assert: Verify setters work correctly
        assertEquals(2, meeting.getMeetingId());
        assertEquals("Client Call", meeting.getDescription());
        assertEquals("Discuss contract terms", meeting.getAgenda());
        assertEquals(room, meeting.getMeetingRoom());
        assertEquals(LocalDateTime.of(2025, 3, 1, 14, 0), meeting.getStartTime());
        assertEquals(LocalDateTime.of(2025, 3, 1, 15, 0), meeting.getEndTime());
        assertFalse(meeting.getIsValid());
        assertTrue(meeting.getStatuses().isEmpty());
    }

    @Test
    void testConstructorAndGetters() {
        // Arrange: Create test data
        MeetingModel meeting = new MeetingModel();
        EmployeeModel employee = new EmployeeModel();
        Boolean meetingStatus = true;

        // Act: Create an instance using the constructor
        EmployeeMeetingStatusModel statusModel = new EmployeeMeetingStatusModel(meeting, meetingStatus, employee);

        // Assert: Verify field values
        assertEquals(meeting, statusModel.getMeeting());
        assertEquals(meetingStatus, statusModel.getMeetingStatus());
        assertEquals(employee, statusModel.getEmployee());
    }

    @Test
    void testSettersEmployeeMeetingStatus() {
        // Arrange: Create an empty instance
        EmployeeMeetingStatusModel statusModel = new EmployeeMeetingStatusModel();

        // Act: Set values
        statusModel.setId(1);
        MeetingModel meeting = new MeetingModel();
        statusModel.setMeeting(meeting);
        statusModel.setMeetingStatus(false);
        EmployeeModel employee = new EmployeeModel();
        statusModel.setEmployee(employee);

        // Assert: Verify setters work correctly
        assertEquals(1, statusModel.getId());
        assertEquals(meeting, statusModel.getMeeting());
        assertFalse(statusModel.getMeetingStatus());
        assertEquals(employee, statusModel.getEmployee());
    }

    @Test
    void testConstructorAndGettersEmployee() {
        // Arrange: Create test data
        OfficeModel office = new OfficeModel();
        String employeeName = "John Doe";
        String employeeEmail = "john.doe@example.com";
        String department = "Engineering";
        Boolean isActive = true;
        int salary = 50000;

        // Act: Create an instance using the constructor
        EmployeeModel employee = new EmployeeModel(employeeName, employeeEmail, office, department, isActive, salary);

        // Assert: Verify field values
        assertEquals(employeeName, employee.getEmployeeName());
        assertEquals(employeeEmail, employee.getEmployeeEmail());
        assertEquals(office, employee.getOffice());
        assertEquals(department, employee.getDepartment());
        assertEquals(isActive, employee.getIsActive());
        assertEquals(salary, employee.getSalary());
    }

    @Test
    void testConstructorWithIdEmployee() {
        // Arrange: Create test data
        OfficeModel office = new OfficeModel();
        int employeeId = 1;
        String employeeName = "Alice Smith";
        String employeeEmail = "alice.smith@example.com";
        String department = "HR";
        Boolean isActive = false;
        int salary = 60000;

        // Act: Create an instance using the constructor with ID
        EmployeeModel employee = new EmployeeModel(employeeId, employeeName, employeeEmail, office, department, isActive, salary);

        // Assert: Verify field values
        assertEquals(employeeId, employee.getEmployeeId());
        assertEquals(employeeName, employee.getEmployeeName());
        assertEquals(employeeEmail, employee.getEmployeeEmail());
        assertEquals(office, employee.getOffice());
        assertEquals(department, employee.getDepartment());
        assertEquals(isActive, employee.getIsActive());
        assertEquals(salary, employee.getSalary());
    }

    @Test
    void testSettersEmployee() {
        // Arrange: Create an empty instance
        EmployeeModel employee = new EmployeeModel();

        // Act: Set values
        employee.setEmployeeId(2);
        employee.setEmployeeName("Bob Johnson");
        employee.setEmployeeEmail("bob.johnson@example.com");
        OfficeModel office = new OfficeModel();
        employee.setOffice(office);
        employee.setDepartment("Finance");
        employee.setIsActive(true);
        employee.setSalary(70000);
        Set<EmployeeMeetingStatusModel> meetingStatuses = new HashSet<>();
        employee.setMeetingStatuses(meetingStatuses);

        // Assert: Verify setters work correctly
        assertEquals(2, employee.getEmployeeId());
        assertEquals("Bob Johnson", employee.getEmployeeName());
        assertEquals("bob.johnson@example.com", employee.getEmployeeEmail());
        assertEquals(office, employee.getOffice());
        assertEquals("Finance", employee.getDepartment());
        assertTrue(employee.getIsActive());
        assertEquals(70000, employee.getSalary());
        assertEquals(meetingStatuses, employee.getMeetingStatuses());
    }

    @Test
    void testConstructorAndGettersMeetingRoomModel() {
        // Arrange: Create test data
        OfficeModel office = new OfficeModel();
        String roomName = "Conference Room 101";
        boolean isEnable = true;

        // Act: Create an instance using the constructor
        MeetingRoomModel meetingRoom = new MeetingRoomModel(roomName, office, isEnable);

        // Assert: Verify field values
        assertEquals(roomName, meetingRoom.getRoomName());
        assertEquals(office, meetingRoom.getOffice());
        assertEquals(isEnable, meetingRoom.isEnable());
    }

    @Test
    void testConstructorWithIdMeetingRoomModel() {
        // Arrange: Create test data
        OfficeModel office = new OfficeModel();
        int roomId = 1;
        String roomName = "Boardroom";
        boolean isEnable = false;

        // Act: Create an instance using the constructor with ID
        MeetingRoomModel meetingRoom = new MeetingRoomModel(roomId, roomName, office, isEnable);

        // Assert: Verify field values
        assertEquals(roomId, meetingRoom.getRoomId());
        assertEquals(roomName, meetingRoom.getRoomName());
        assertEquals(office, meetingRoom.getOffice());
        assertEquals(isEnable, meetingRoom.isEnable());
    }

    @Test
    void testSettersMeetingRoomModel() {
        // Arrange: Create an empty instance
        MeetingRoomModel meetingRoom = new MeetingRoomModel();

        // Act: Set values
        meetingRoom.setRoomId(2);
        meetingRoom.setRoomName("Training Room");
        OfficeModel office = new OfficeModel();
        meetingRoom.setOffice(office);
        meetingRoom.setEnable(true);
        List<MeetingModel> meetings = new ArrayList<>();
        meetingRoom.setMeetings(meetings);

        // Assert: Verify setters work correctly
        assertEquals(2, meetingRoom.getRoomId());
        assertEquals("Training Room", meetingRoom.getRoomName());
        assertEquals(office, meetingRoom.getOffice());
        assertTrue(meetingRoom.isEnable());
        assertEquals(meetings, meetingRoom.getMeetings());
    }

    @Test
    void testConstructorAndGettersOfficeModel() {
        // Arrange: Create test data
        int officeId = 1;
        String officeName = "Headquarters";
        String officeLocation = "New York";

        // Act: Create an instance using the constructor
        OfficeModel office = new OfficeModel(officeId, officeName, officeLocation);

        // Assert: Verify field values
        assertEquals(officeId, office.getOfficeId());
        assertEquals(officeName, office.getOfficeName());
        assertEquals(officeLocation, office.getOfficeLocation());
        assertNotNull(office.getMeetingRooms());  // Should not be null
        assertNotNull(office.getEmployees());     // Should not be null
    }

    @Test
    void testSettersOfficeModel() {
        // Arrange: Create an empty instance
        OfficeModel office = new OfficeModel();

        // Act: Set values
        office.setOfficeId(2);
        office.setOfficeName("Branch Office");
        office.setOfficeLocation("Los Angeles");

        List<MeetingRoomModel> meetingRooms = new ArrayList<>();
        office.setMeetingRooms(meetingRooms);

        List<EmployeeModel> employees = new ArrayList<>();
        office.setEmployees(employees);

        // Assert: Verify setters work correctly
        assertEquals(2, office.getOfficeId());
        assertEquals("Branch Office", office.getOfficeName());
        assertEquals("Los Angeles", office.getOfficeLocation());
        assertEquals(meetingRooms, office.getMeetingRooms());
        assertEquals(employees, office.getEmployees());
    }

    @Test
    void testDefaultListsNotNullOfficeModel() {
        // Act: Create an instance using default constructor
        OfficeModel office = new OfficeModel();

        // Assert: Ensure meetingRooms and employees lists are initialized
        assertNotNull(office.getMeetingRooms(), "Meeting rooms list should be initialized");
        assertNotNull(office.getEmployees(), "Employees list should be initialized");
    }

}
