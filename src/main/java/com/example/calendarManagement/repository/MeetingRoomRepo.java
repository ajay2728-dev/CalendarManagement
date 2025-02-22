package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.MeetingRoomModel;
import com.example.calendarManagement.model.OfficeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetingRoomRepo  extends JpaRepository<MeetingRoomModel,Integer> {
    int countByOffice(OfficeModel office);

    Optional<MeetingRoomModel> findByRoomName(String roomName);
}
