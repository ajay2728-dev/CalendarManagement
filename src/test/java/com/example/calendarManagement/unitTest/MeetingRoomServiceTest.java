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

    @InjectMocks
    private MeetingRoomValidator meetingRoomValidator;

    @BeforeEach
    public void setup(){
        office =  new OfficeModel(1,"Headquarters","New York");
        inputMeetingRoom = new MeetingRoomRequestDTO(0, "Alpha Conference", 1, true);
        missingInputMeetingRoom = new MeetingRoomRequestDTO(0,null,0,true);
        saveMeetingRoom = new MeetingRoomModel(1, "Alpha Conference", office, true);
    }

    @Test
    void test_whenAddMeetingRoom_givenValidInput_addMeetingRoomSuccess()  {

        Mockito.when(meetingRoomRepo.save(Mockito.any(MeetingRoomModel.class))).thenReturn(saveMeetingRoom);

        MeetingRoomModel result = meetingRoomService.addMeetingRoom(inputMeetingRoom,office);

        assertThat(result).isNotNull();
        assertThat(result.getOffice()).isEqualTo(office);
        assertThat(result.getRoomName()).isEqualTo("Alpha Conference");

    }

    @Test
    void test_whenAddMeetingRoom_givenInvalidOfficeId_throwMissingFieldException(){

        MissingFieldException thrownException = assertThrows(MissingFieldException.class,()->{
            meetingRoomValidator.validatorAddMeetingRoom(missingInputMeetingRoom);
                }
        );
        assertEquals("Missing Required Input",thrownException.getMessage());

    }

    @Test
    void test_whenAddMeetingRoom_givenValidInput_throwNotFoundException(){

        Mockito.when(officeRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
            meetingRoomValidator.validatorAddMeetingRoom(inputMeetingRoom);
        });

        assertEquals("provided office not found",thrownException.getMessage());
    }

    @Test
    void test_validatorAddMeetingRoom_whenRoomNameAlreadyExists_throwsConstraintViolationException() {
        Mockito.when(officeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(new OfficeModel()));
        Mockito.when(meetingRoomRepo.findByRoomName(Mockito.anyString())).thenReturn(Optional.of(new MeetingRoomModel()));

        ConstraintViolationException thrownException = assertThrows(ConstraintViolationException.class, () ->
                meetingRoomValidator.validatorAddMeetingRoom(inputMeetingRoom)
        );

        assertEquals("Give Different Room Name", thrownException.getMessage());
    }

    @Test
    void test_whenAddMeetingRoom_whenRoomLimitExceeded_throwConstraintViolationException(){
        Mockito.when(officeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(office));
        Mockito.when(meetingRoomRepo.countByOffice(office)).thenReturn(10);

        ConstraintViolationException thrownException = assertThrows(ConstraintViolationException.class,()->{
            meetingRoomValidator.validatorAddMeetingRoom(inputMeetingRoom);
        });

        assertEquals("Office Already have 10 meeting room",thrownException.getMessage());
    }

    @Test
    void test_whenUpdateMeetingRoomStatusToEnable_givenValidInput_updateStatusToEnableSuccess(){

        Mockito.when(meetingRoomRepo.save(Mockito.any(MeetingRoomModel.class))).thenReturn(saveMeetingRoom);
        saveMeetingRoom.setEnable(false);
        MeetingRoomModel result = meetingRoomService.updateMeetingRoomStatusToDisable(saveMeetingRoom);

        assertThat(result).isNotNull();
        assertThat(result.getIsEnable()).isEqualTo(false);

    }

    @Test
    void test_whenUpdateMeetingRoomStatusToEnable_whenRoomNotFound_throwsNotFoundException(){

        Mockito.when(meetingRoomRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        NotFoundException thrownException = assertThrows(NotFoundException.class, () ->
                meetingRoomValidator.validatorUpdateMeetingRoomStatusToEnable(1)
        );

        assertEquals("Room Not Found with given meetingId", thrownException.getMessage());

    }

    @Test
    void test_whenUpdateMeetingRoomStatusToEnable_whenRoomAlreadyEnabled_throwsConstraintViolationException(){
        Mockito.when(meetingRoomRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(saveMeetingRoom));

        ConstraintViolationException thrownException = assertThrows(ConstraintViolationException.class, () ->
                meetingRoomValidator.validatorUpdateMeetingRoomStatusToEnable(1)
        );

        assertEquals("Meeting Room already Enable", thrownException.getMessage());

    }

//    @Test
//    void test_whenUpdateMeetingRoomStatusToEnable_WhenRoomAlreadyEnabled_ThrowsConstraintViolationException(){
//        Mockito.when(meetingRoomRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(saveMeetingRoom));
//
//        ConstraintViolationException thrownException = assertThrows(ConstraintViolationException.class, () ->
//                meetingRoomValidator.ValidatorUpdateMeetingRoomStatusToEnable(1)
//        );
//
//        assertEquals("Meeting Room already Enable", thrownException.getMessage());
//
//    }

//    @Test
//    void test_whenUpdateMeetingRoomStatusToEnable_givenMissingInput_ThrowMissingFieldException(){
//
//        MissingFieldException thrownException = assertThrows(MissingFieldException.class,()->{
//                    meetingRoomService.updateStatusMeetingRoom(missingInputMeetingRoom);
//                }
//        );
//        assertEquals("Missing Required Input",thrownException.getMessage());
//    }

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
    void test_whenGettingRoomById_givenInValidRoomId_throwNotFoundException(){
        Mockito.when(meetingRoomRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
            meetingRoomService.getMeetingRoomById(100);
        });

        assertEquals("Meeting room not found",thrownException.getMessage());

    }


}
