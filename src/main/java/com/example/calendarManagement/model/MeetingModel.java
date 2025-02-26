package com.example.calendarManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "meetingId")
    private int meetingId;

    @Column( name = "description", nullable = false)
    private String description;

    @Column( name = "agenda",nullable = false)
    private String agenda;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private MeetingRoomModel meetingRoom;

    @Column( name = "startTime",nullable = false)
    private LocalDateTime startTime;

    @Column( name = "endTime",nullable = false)
    private LocalDateTime endTime;

    @Column( name = "isValid", nullable = false)
    private Boolean isValid;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<EmployeeMeetingStatusModel> statuses;

    public MeetingModel(int meetingId, String description, String agenda, MeetingRoomModel meetingRoom, LocalDateTime startTime, LocalDateTime endTime, Boolean isValid) {
        this.meetingId = meetingId;
        this.description = description;
        this.agenda = agenda;
        this.meetingRoom = meetingRoom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isValid = isValid;
    }

}
