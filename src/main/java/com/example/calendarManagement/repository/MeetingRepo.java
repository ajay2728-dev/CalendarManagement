package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.MeetingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetingRepo extends JpaRepository<MeetingModel,Integer> {
    @Query("SELECT m FROM MeetingModel m WHERE m.meetingId = :meetingId AND m.isValid = true")
    Optional<MeetingModel> findValidMeeting(@Param("meetingId") int meetingId);

}
