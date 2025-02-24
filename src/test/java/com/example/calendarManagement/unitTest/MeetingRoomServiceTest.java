package com.example.calendarManagement.unitTest;

import com.example.calendarManagement.dto.MeetingRoomRequestDTO;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.MeetingRoomModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.repository.MeetingRoomRepo;
import com.example.calendarManagement.repository.OfficeRepo;
import com.example.calendarManagement.service.MeetingRoomService;
import com.example.calendarManagement.validator.MeetingRoomValidator;
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

    @Mock
    private MeetingRoomValidator meetingRoomValidator;

    @BeforeEach
    public void setup(){
        office =  new OfficeModel(1,"Headquarters","New York");
        inputMeetingRoom = new MeetingRoomRequestDTO(0, "Alpha Conference", 1, true);
        missingInputMeetingRoom = new MeetingRoomRequestDTO(0,null,0,true);
        saveMeetingRoom = new MeetingRoomModel(1, "Alpha Conference", office, true);
    }

    @Test
    void test_whenAddMeetingRoom_givenValidInput_AddMeetingRoomSuccess()  {

        Mockito.when(meetingRoomRepo.save(Mockito.any(MeetingRoomModel.class))).thenReturn(saveMeetingRoom);

        MeetingRoomModel result = meetingRoomService.addMeetingRoom(inputMeetingRoom,office);

        assertThat(result).isNotNull();
        assertThat(result.getOffice()).isEqualTo(office);
        assertThat(result.getRoomName()).isEqualTo("Alpha Conference");

    }

    @Test
    void test_whenAddMeetingRoom_givenInvalidOfficeId_ThrowMissingFieldException(){

        MissingFieldException thrownException = assertThrows(MissingFieldException.class,()->{
            meetingRoomValidator.ValidatorAddMeetingRoom(missingInputMeetingRoom);
                }
        );
        assertEquals("Missing Required Input",thrownException.getMessage());

    }

    @Test
    void test_whenAddMeetingRoom_givenValidInput_ThrowNotFoundException(){

        Mockito.when(officeRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
            meetingRoomService.addMeetingRoom(inputMeetingRoom,office);
        });
    }

    @Test
    void test_whenAddMeetingRoom_WhenRoomLimitExceeded_ThrowConstraintViolationException(){
        Mockito.when(officeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(office));
        Mockito.when(meetingRoomRepo.countByOffice(office)).thenReturn(10);

        ConstraintViolationException thrownException = assertThrows(ConstraintViolationException.class,()->{
            meetingRoomService.addMeetingRoom(inputMeetingRoom,office);
        });
    }

//    @Test
//    void test_whenUpdateMeetingRoomStatus_givenValidInput_updateStatusSuccess(){
//
//        Mockito.when(meetingRoomRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(saveMeetingRoom));
//        Mockito.when(meetingRoomRepo.save(Mockito.any(MeetingRoomModel.class))).thenReturn(new MeetingRoomModel(1, "Alpha Conference", office, false));
//
//        MeetingRoomModel result = meetingRoomService.updateStatusMeetingRoom(new MeetingRoomRequestDTO(1, "Alpha Conference", 1, true));
//
//        assertThat(result).isNotNull();
//        assertThat(result.getIsEnable()).isEqualTo(false);
//    }
//
//    @Test
//    void test_whenUpdateMeetingRoomStatus_givenMissingInput_ThrowMissingFieldException(){
//
//        MissingFieldException thrownException = assertThrows(MissingFieldException.class,()->{
//                    meetingRoomService.updateStatusMeetingRoom(missingInputMeetingRoom);
//                }
//        );
//        assertEquals("Missing Required Input",thrownException.getMessage());
//    }
//
//    @Test
//    void test_whenUpdateMeetingRoomStatus_inValidRoomId_ThrowNotFoundException(){
//        Mockito.when(meetingRoomRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
//
//        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
//            meetingRoomService.updateStatusMeetingRoom(new MeetingRoomRequestDTO(1, "Alpha Conference", 1, true));
//        });
//
//        assertEquals("Room Not Found",thrownException.getMessage());
//
//    }

    @Test
    void test_whenGettingRoomById_giveValidRoomId_getRoomSuccess(){
        Mockito.when(meetingRoomRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(saveMeetingRoom));

        MeetingRoomModel result = meetingRoomService.getMeetingRoomById(1);

        assertThat(result).isNotNull();
        assertThat(result.getRoomId()).isEqualTo(1);
    }

    @Test
    void test_whenGettingRoomById_givenInValidRoomId_ThrowNotFoundException(){
        Mockito.when(meetingRoomRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
            meetingRoomService.getMeetingRoomById(100);
        });

        assertEquals("Meeting room not found",thrownException.getMessage());

    }



}
