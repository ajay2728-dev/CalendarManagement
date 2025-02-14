package com.example.calendarManagement.unitTest;

import com.example.calendarManagement.dto.MeetingRoomRequestDTO;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.model.MeetingRoomModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.repository.MeetingRoomRepo;
import com.example.calendarManagement.repository.OfficeRepo;
import com.example.calendarManagement.service.MeetingRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MeetingRoomServiceTest {

    private MeetingRoomRequestDTO inputMeetingRoom;
    private MeetingRoomModel saveMeetingRoom;
    private MeetingRoomRequestDTO missingInputMeetingRoom;
    private OfficeModel office;

    @InjectMocks
    private MeetingRoomService meetingRoomService;

    @Mock
    private MeetingRoomRepo meetingRoomRepo;

    @Mock
    private OfficeRepo officeRepo;

    @BeforeEach
    public void setup(){
        office =  new OfficeModel(1,"Headquarters","New York");
        inputMeetingRoom = new MeetingRoomRequestDTO(0, "Alpha Conference", 1, true);
        missingInputMeetingRoom = new MeetingRoomRequestDTO(0,null,1,true);
        saveMeetingRoom = new MeetingRoomModel(1, "Alpha Conference", office, true);
    }

    @Test
    void test_whenAddMeetingRoom_givenValidInput_AddMeetingRoomSuccess()  {

        Mockito.when(officeRepo.findById(1)).thenReturn(Optional.of(office));
        Mockito.when(meetingRoomRepo.countByOffice(office)).thenReturn(8);
        Mockito.when(meetingRoomRepo.save(Mockito.any(MeetingRoomModel.class))).thenReturn(saveMeetingRoom);

        MeetingRoomModel result = meetingRoomService.addMeetingRoom(inputMeetingRoom);

        assertThat(result).isNotNull();
        assertThat(result.getOffice()).isEqualTo(office);
        assertThat(result.getRoomName()).isEqualTo("Alpha Conference");

    }

    @Test
    void test_whenAddMeetingRoom_givenMissingInput_ThrowMissingFieldException(){

        MissingFieldException thrownException = assertThrows(MissingFieldException.class,()->{
                    meetingRoomService.addMeetingRoom(missingInputMeetingRoom);
                }
        );
        assertEquals("Missing Required Input",thrownException.getMessage());

    }

    @Test
    void test_whenAddMeetingRoom_inValidOfficeId_ThrowNotFoundException(){

        Mockito.when(officeRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
            meetingRoomService.addMeetingRoom(inputMeetingRoom);
        });
    }

    @Test
    void test_whenAddMeetingRoom_WhenRoomLimitExceeded_ThrowConstraintViolationException(){
        Mockito.when(officeRepo.findById(1)).thenReturn(Optional.of(office));
        Mockito.when(meetingRoomRepo.countByOffice(office)).thenReturn(10);

        ConstraintViolationException thrownException = assertThrows(ConstraintViolationException.class,()->{
            meetingRoomService.addMeetingRoom(inputMeetingRoom);
        });
    }

}
