package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.MeetingStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MeetingStatusRepo extends JpaRepository<MeetingStatusModel,Integer> {
    @Query(value = "SELECT * FROM meeting_status ms JOIN meeting_status_employee mse ON ms.statusId = mse.statusId WHERE mse.employeeId = :employeeId AND ms.meetingId = :meetingId", nativeQuery = true)
    Optional<MeetingStatusModel> findMeetingStatusByEmployeeAndMeeting(
            @Param("employeeId") int employeeId,
            @Param("meetingId") int meetingId
    );
}
