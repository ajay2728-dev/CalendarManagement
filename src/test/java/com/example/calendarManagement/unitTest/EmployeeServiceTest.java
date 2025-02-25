package com.example.calendarManagement.unitTest;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.dto.EmployeeResponseDTO;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.InvalidFieldException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.objectMapper.EmployeeModelToEmployeeResponseDTO;
import com.example.calendarManagement.repository.EmployeeRepo;
import com.example.calendarManagement.repository.OfficeRepo;
import com.example.calendarManagement.service.EmployeeService;
import com.example.calendarManagement.validator.EmployeeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private EmployeeModel savedEmployee;
    private EmployeeRequestDTO inputEmployee;
    private EmployeeRequestDTO missingInputEmployee;
    private EmployeeRequestDTO invalidEmailEmployee;
    private OfficeModel office;
    List<EmployeeModel> mockEmployees;
    private EmployeeResponseDTO expectedResponse;

    @Mock
    EmployeeRepo employeeRepo;

    @InjectMocks
    EmployeeService employeeService;

    @InjectMocks
    EmployeeValidator employeeValidator;

    @Mock
    EmployeeModelToEmployeeResponseDTO employeeMapper;

    @Mock
    OfficeRepo officeRepo;

    @BeforeEach
    void setup(){

        office =  new OfficeModel(1,"Headquarters","New York");

        inputEmployee = new EmployeeRequestDTO(0, "John Doe", 1,
                "john.doe@xyz.com", true, 50000, "Engineering");

        savedEmployee = new EmployeeModel( 1,"John Doe", "john.doe@xyz.com", office,"Engineering",
                true, 50000 );

        missingInputEmployee = new EmployeeRequestDTO(1, "John Doe", 1,
                null, true, 50000, "Engineering");

        invalidEmailEmployee = new EmployeeRequestDTO(1, "John Doe", 1,
                "john.doe.com", true, 50000, "Engineering");
        
        mockEmployees = Arrays.asList(
                new EmployeeModel("John Doe", "john.doe@xyz.com", office,"Engineering",
                        true, 50000),
                new EmployeeModel("Ajay Singh", "ajay.singh@xyz.com",
                        office, "Engineering", true, 30000)
        );

        expectedResponse = new EmployeeResponseDTO(1, "John Doe", 1,
                "john.doe@xyz.com", 30000, true, "Engineering");


    }

    @Test
    public void test_whenAddEmployee_givenValidEmployee_addEmployeeSuccess()  {
       Mockito.when(employeeRepo.save(Mockito.any(EmployeeModel.class))).thenReturn(savedEmployee);
       Mockito.when(employeeMapper.map(savedEmployee)).thenReturn(expectedResponse);

       EmployeeResponseDTO actualResponse = employeeService.addEmployee(inputEmployee, office);
       assertThat(actualResponse).isNotNull();
       assertThat(actualResponse.getEmployeeName()).isEqualTo(inputEmployee.getEmployeeName());
    }

    @Test
    void addEmployee_Failure() {
        Mockito.when(employeeRepo.save(Mockito.any(EmployeeModel.class))).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            employeeService.addEmployee(inputEmployee, office);
        });

        assertTrue(exception.getMessage().contains("An error occurred while saving the employee"));
    }

    @Test
    public void test_whenAddEmployee_givenMissingInput_throwEmployeeMissingInputException(){
        MissingFieldException thrownException = assertThrows(MissingFieldException.class,()->{
            employeeValidator.addEmployeeValidator(missingInputEmployee);
                }
        );
        assertEquals("Missing Required Input",thrownException.getMessage());
    }

    @Test
    public void test_whenAddEmployee_givenInvalidEmail_throwEmployeeInvalidInputException() {

        InvalidFieldException thrownException = assertThrows(InvalidFieldException.class,()->{
            employeeValidator.addEmployeeValidator(invalidEmailEmployee);
                }
        );
        assertEquals("Invalid Email Format",thrownException.getMessage());

    }

    @Test
    public void test_whenAddEmployee_whenEmailAlreadyExistsAndActive_throwConstraintViolationException(){
        Mockito.when(employeeRepo.findByEmployeeEmail(Mockito.any()))
                .thenReturn(Optional.of(savedEmployee));

        assertThrows(ConstraintViolationException.class, () ->
                employeeValidator.addEmployeeValidator(inputEmployee)
        );
    }

    @Test
    public void test_whenAddEmployee_whenEmailAlreadyExistsAndInactive_throwConstraintViolationException(){
        savedEmployee.setIsActive(false);
        Mockito.when(employeeRepo.findByEmployeeEmail(Mockito.any()))
                .thenReturn(Optional.of(savedEmployee));

        assertThrows(ConstraintViolationException.class, () ->
                employeeValidator.addEmployeeValidator(inputEmployee)
        );
    }

    @Test
    public void test_whenOfficeNotFound_throwNotFoundException(){
        Mockito.when(employeeRepo.findByEmployeeEmail(Mockito.any()))
                .thenReturn(Optional.empty());

        Mockito.when(officeRepo.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                employeeValidator.addEmployeeValidator(inputEmployee)
        );
    }


    @Test
    public void test_whenGetEmployeeById_givenValidId_getEmployeeSuccess()  {

        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(savedEmployee));
        Mockito.when(employeeMapper.map(savedEmployee)).thenReturn(expectedResponse);

        EmployeeResponseDTO result = employeeService.getEmployeeById(1);

        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo(1);
        assertThat(result.getEmployeeEmail()).isEqualTo("john.doe@xyz.com");
    }

    @Test
    public void test_whenGetEmployeeById_givenValidId_throwNotFoundEmployeeException(){

        Mockito.when(employeeRepo.findById(100)).thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
            employeeService.getEmployeeById(100);
        });

        assertEquals("No employee exists with the given ID.",thrownException.getMessage());

    }

    @Test
    public void test_whenEmployeeExistsButInactive_thenThrowNotFoundException() {

        savedEmployee.setIsActive(false);
        Mockito.when(employeeRepo.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(savedEmployee));

        assertThrows(NotFoundException.class, () ->
                employeeService.getEmployeeById(1)
        );
    }

    @Test
    public void test_WhenGetAllEmployee_retrievedSuccess(){
        Mockito.when(employeeRepo.findByIsActiveTrue()).thenReturn(mockEmployees);

        List<EmployeeResponseDTO> result = employeeService.getAllEmployee();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getEmployeeEmail()).isEqualTo("john.doe@xyz.com");
        assertThat(result.get(1).getEmployeeEmail()).isEqualTo("ajay.singh@xyz.com");

    }

    @Test
    public void test_WhenDeleteEmployee_givenValidId_deleteEmployeeSuccess() throws Exception {
        savedEmployee.setIsActive(false);
        Mockito.when(employeeRepo.save(Mockito.any(EmployeeModel.class))).thenReturn(savedEmployee);
        EmployeeModel result = employeeService.deleteEmployeeById(savedEmployee);

        assertThat(result).isNotNull();
        assertThat(result.getIsActive()).isEqualTo(false);
    }

    @Test
    public void test_WhenEmployeeDoesNotExist_shouldThrowNotFoundException(){
        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        NotFoundException thrownException = assertThrows(NotFoundException.class, () -> employeeValidator.deleteEmployeeValidator(100));
        assertEquals("Employee Does not Exit with Given ID",thrownException.getMessage());
    }

    @Test
    public void test_WhenEmployeeAlreadyDeleted_shouldThrowNotFoundException() {
        savedEmployee.setIsActive(false);
        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(savedEmployee));

        NotFoundException thrownException =  assertThrows(NotFoundException.class, () -> employeeValidator.deleteEmployeeValidator(1));
        assertEquals("Employee already deleted.",thrownException.getMessage());
    }

}
