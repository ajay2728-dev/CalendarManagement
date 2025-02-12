package com.example.calendarManagement;

import com.example.calendarManagement.exception.EmployeeInvalidEmailException;
import com.example.calendarManagement.exception.EmployeeMissingInputException;
import com.example.calendarManagement.exception.NonUniqueEmployeeEmailException;
import com.example.calendarManagement.exception.NotFoundEmployeeException;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.repository.EmployeeRepo;
import com.example.calendarManagement.service.EmployeeService;
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
public class EmployeeServiceTest {

    private EmployeeModel savedEmployee;
    private EmployeeModel inputEmployee;
    private EmployeeModel missingInputEmployee;
    private EmployeeModel invalidEmailEmployee;

    @Mock
    EmployeeRepo employeeRepo;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setup(){

        inputEmployee = new EmployeeModel(0, "John Doe", "New York",
                "john.doe@xyz.com", true, 50000, "Engineering");

        savedEmployee = new EmployeeModel(1, "John Doe", "New York",
                "john.doe@xyz.com", true, 50000, "Engineering");

        missingInputEmployee = new EmployeeModel(1, "John Doe", "New York",
                null, true, 50000, "Engineering");

        invalidEmailEmployee = new EmployeeModel(1, "John Doe", "New York",
                "john.doe.com", true, 50000, "Engineering");


    }

    @Test
    public void test_whenAddEmployee_givenValidEmployee_AddEmployeeSuccess()  {

        Mockito.when(employeeRepo.findByEmployeeEmail("john.doe@xyz.com")).thenReturn(Optional.empty());
        Mockito.when(employeeRepo.save(Mockito.any(EmployeeModel.class))).thenReturn(savedEmployee);

        EmployeeModel result= employeeService.addEmployee(inputEmployee);

        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo(1);
        assertThat(result.getEmployeeName()).isEqualTo("John Doe");
        assertThat(result.getEmployeeEmail()).isEqualTo("john.doe@xyz.com");
    }

    @Test
    public void test_whenAddEmployee_givenMissingInput_ThrowEmployeeMissingInputException(){
        EmployeeMissingInputException thrownException = assertThrows(EmployeeMissingInputException.class,()->{
                    employeeService.addEmployee(missingInputEmployee);
                }
        );
        assertEquals("Missing Required Input",thrownException.getMessage());
    }

    @Test
    public void test_whenAddEmployee_givenInvalidEmail_ThrowEmployeeInvalidInputException() {

        EmployeeInvalidEmailException thrownException = assertThrows(EmployeeInvalidEmailException.class,()->{
                    employeeService.addEmployee(invalidEmailEmployee);
                }
        );
        assertEquals("Invalid Email Format",thrownException.getMessage());

    }

    @Test
    public void test_whenAddEmployee_givenNonUniqueEmployeeEmail_ThrowNonUniqueEmployeeEmailException() {

        Mockito.when(employeeRepo.findByEmployeeEmail("john.doe@xyz.com")).thenReturn(Optional.of(new EmployeeModel(1, "John Doe", "john.doe@xyz.com",
                "New York", true, 30000, "Engineering")));

        NonUniqueEmployeeEmailException thrownException = assertThrows(NonUniqueEmployeeEmailException.class,()->{
                    employeeService.addEmployee(inputEmployee);
                }
        );
        assertEquals("Provide Different Employee Email",thrownException.getMessage());

    }

    @Test
    public void test_WhenGetEmployeeById_givenValidId_GetEmployeeSuccess()  {

        Mockito.when(employeeRepo.findById(1)).thenReturn(Optional.of(new EmployeeModel(1, "John Doe", "New York" ,
                "john.doe@xyz.com", true, 50000, "Engineering")));

        EmployeeModel result = employeeService.getEmployeeById(1);

        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo(1);
        assertThat(result.getEmployeeEmail()).isEqualTo("john.doe@xyz.com");
    }

    @Test
    public void test_WhenGetEmployeeById_givenValidId_ThrowNotFoundEmployeeException(){

        Mockito.when(employeeRepo.findById(100)).thenReturn(Optional.empty());

        NotFoundEmployeeException thrownException = assertThrows(NotFoundEmployeeException.class,()->{
            employeeService.getEmployeeById(100);
        });

        assertEquals("Employee Not Found",thrownException.getMessage());

    }
}
