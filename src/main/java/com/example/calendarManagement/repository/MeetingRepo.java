package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.MeetingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepo extends JpaRepository<MeetingModel,Integer> {
}
