package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.EmployeeMeetingStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeMeetingStatusRepo extends JpaRepository<EmployeeMeetingStatusModel,Integer> {
    @Query(value = "SELECT * FROM employee_meeting_status_model ms WHERE ms.employee_id = :employeeId AND ms.meeting_id = :meetingId", nativeQuery = true)
    Optional<EmployeeMeetingStatusModel> findMeetingStatusByEmployeeAndMeeting(
            @Param("employeeId") int employeeId,
            @Param("meetingId") int meetingId
    );

    @Modifying
    @Query("UPDATE EmployeeMeetingStatusModel ms SET ms.meetingStatus = false WHERE ms.meeting.meetingId = :meetingId")
    void updateMeetingStatusToFalse(@Param("meetingId") int meetingId);

}
