package com.example.calendarManagement.unitTest;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.exception.InvalidFieldException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NonUniqueFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.repository.EmployeeRepo;
import com.example.calendarManagement.service.EmployeeService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private EmployeeModel savedEmployee;
    private EmployeeRequestDTO inputEmployee;
    private EmployeeRequestDTO missingInputEmployee;
    private EmployeeRequestDTO invalidEmailEmployee;
    private OfficeModel office;
    List<EmployeeModel> mockEmployees;

    @Mock
    EmployeeRepo employeeRepo;

    @InjectMocks
    EmployeeService employeeService;

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


    }

    @Test
    public void test_whenAddEmployee_givenValidEmployee_AddEmployeeSuccess()  {

        Mockito.when(employeeRepo.findByEmployeeEmail("john.doe@xyz.com")).thenReturn(Optional.empty());
        Mockito.when(employeeRepo.save(Mockito.any(EmployeeModel.class))).thenReturn(savedEmployee);

        EmployeeModel result = employeeService.addEmployee(inputEmployee);

        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo(1);
        assertThat(result.getEmployeeName()).isEqualTo("John Doe");
        assertThat(result.getEmployeeEmail()).isEqualTo("john.doe@xyz.com");
    }

    @Test
    public void test_whenAddEmployee_givenMissingInput_ThrowEmployeeMissingInputException(){
        MissingFieldException thrownException = assertThrows(MissingFieldException.class,()->{
                    employeeService.addEmployee(missingInputEmployee);
                }
        );
        assertEquals("Missing Required Input",thrownException.getMessage());
    }

    @Test
    public void test_whenAddEmployee_givenInvalidEmail_ThrowEmployeeInvalidInputException() {

        InvalidFieldException thrownException = assertThrows(InvalidFieldException.class,()->{
                    employeeService.addEmployee(invalidEmailEmployee);
                }
        );
        assertEquals("Invalid Email Format",thrownException.getMessage());

    }

    @Test
    public void test_whenAddEmployee_givenNonUniqueEmployeeEmail_ThrowNonUniqueEmployeeEmailException() {

        Mockito.when(employeeRepo.findByEmployeeEmail("john.doe@xyz.com")).thenReturn(Optional.of(new EmployeeModel(1,"John Doe", "john.doe@xyz.com", office,"Engineering",
                true, 50000)));

        NonUniqueFieldException thrownException = assertThrows(NonUniqueFieldException.class,()->{
                    employeeService.addEmployee(inputEmployee);
                }
        );
        assertEquals("Provide Different Employee Email",thrownException.getMessage());

    }

    @Test
    public void test_WhenGetEmployeeById_givenValidId_GetEmployeeSuccess()  {

        Mockito.when(employeeRepo.findById(1)).thenReturn(Optional.of(new EmployeeModel(1,"John Doe", "john.doe@xyz.com", office,"Engineering",
                true, 50000)));

        EmployeeModel result = employeeService.getEmployeeById(1);

        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo(1);
        assertThat(result.getEmployeeEmail()).isEqualTo("john.doe@xyz.com");
    }

    @Test
    public void test_WhenGetEmployeeById_givenValidId_ThrowNotFoundEmployeeException(){

        Mockito.when(employeeRepo.findById(100)).thenReturn(Optional.empty());

        NotFoundException thrownException = assertThrows(NotFoundException.class,()->{
            employeeService.getEmployeeById(100);
        });

        assertEquals("No employee exists with the given ID",thrownException.getMessage());

    }

    @Test
    public void test_WhenGetAllEmployee_RetrievedSuccess(){
        Mockito.when(employeeRepo.findAll()).thenReturn(mockEmployees);

        List<EmployeeModel> result = employeeService.getAllEmployee();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getEmployeeEmail()).isEqualTo("john.doe@xyz.com");
        assertThat(result.get(1).getEmployeeEmail()).isEqualTo("ajay.singh@xyz.com");

    }

    @Test
    public void test_WhenDeleteEmployee_givenValidId_DeleteEmployeeSuccess() throws Exception {

       Mockito.when(employeeRepo.findById(1)).thenReturn(Optional.of(new EmployeeModel(1,"John Doe", "john.doe@xyz.com", office,"Engineering",
               true, 50000)));

       Mockito.when(employeeRepo.save(Mockito.any(EmployeeModel.class))).thenReturn(new EmployeeModel(1,"John Doe", "john.doe@xyz.com", office,"Engineering",
               true, 50000));

       EmployeeModel result = employeeService.deleteEmployeeById(1);

       assertThat(result.getIsActive()).isEqualTo(false);
       assertThat(result.getEmployeeId()).isEqualTo(1);

    }

}
