package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.MeetingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepo extends JpaRepository<MeetingModel,Integer> {
    @Query("SELECT m FROM MeetingModel m WHERE m.meetingId = :meetingId AND m.isValid = true")
    Optional<MeetingModel> findValidMeeting(@Param("meetingId") int meetingId);

    @Query("SELECT m FROM MeetingModel m " +
            "JOIN m.statuses ms " +
            "JOIN ms.employees e " +
            "WHERE e.employeeId = :employeeId " +
            "AND m.startTime BETWEEN :startDate AND :endDate " +
            "AND m.isValid = true")

    List<MeetingModel> findByEmployeeIdAndDateRange(
            @Param("employeeId") int employeeId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}
