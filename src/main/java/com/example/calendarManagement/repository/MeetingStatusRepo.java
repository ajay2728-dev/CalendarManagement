package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.MeetingStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MeetingStatusRepo extends JpaRepository<MeetingStatusModel,Integer> {
    @Query(value = "SELECT * FROM meeting_status_model ms JOIN meeting_status_employee mse ON ms.status_id = mse.status_id WHERE mse.employee_id = :employeeId AND ms.meeting_id = :meetingId", nativeQuery = true)
    Optional<MeetingStatusModel> findMeetingStatusByEmployeeAndMeeting(
            @Param("employeeId") int employeeId,
            @Param("meetingId") int meetingId
    );

    @Modifying
    @Query("UPDATE MeetingStatusModel ms SET ms.status = false WHERE ms.meeting.meetingId = :meetingId")
    void updateMeetingStatusToFalse(@Param("meetingId") int meetingId);

}
