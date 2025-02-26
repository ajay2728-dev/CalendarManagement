package com.example.calendarManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRoomModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId")
    private int roomId;

    @Column(name = "roomName", unique = true, nullable = false)
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private OfficeModel office;

    @Column(name = "isEnable", nullable = false)
    private boolean isEnable;

    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeetingModel> meetings;

    public MeetingRoomModel(int roomId, String roomName, OfficeModel office, boolean isEnable) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.office = office;
        this.isEnable = isEnable;
    }

    public MeetingRoomModel(String roomName, OfficeModel office, boolean isEnable) {
        this.roomName = roomName;
        this.office = office;
        this.isEnable = isEnable;
    }

}
