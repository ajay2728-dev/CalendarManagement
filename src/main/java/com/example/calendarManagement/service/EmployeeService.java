package com.example.calendarManagement.service;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.dto.EmployeeResponseDTO;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.InvalidFieldException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.objectMapper.EmployeeMapper;
import com.example.calendarManagement.objectMapper.EmployeeModelToEmployeeRequestDTO;
import com.example.calendarManagement.objectMapper.EmployeeModelToEmployeeResponseDTO;
import com.example.calendarManagement.repository.EmployeeRepo;
import com.example.calendarManagement.repository.OfficeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private OfficeRepo officeRepo;


    // method to add employee
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employee, OfficeModel office) {

        EmployeeModel newEmployee = new EmployeeModel(
                employee.getEmployeeName(),
                employee.getEmployeeEmail(),
                office,
                employee.getDepartment(),
                employee.getActive(),
                employee.getSalary()
        );

        try {
            EmployeeModel saveEmployee = employeeRepo.save(newEmployee);
            EmployeeResponseDTO response = EmployeeModelToEmployeeResponseDTO.map(saveEmployee);
            return response;
        } catch (RuntimeException ex){
            throw new RuntimeException("An error occurred while saving the employee: " + ex.getMessage());
        }
    }

    // method to get employee by id
    public EmployeeResponseDTO getEmployeeById(int employeeId) {

        log.info("searching employee by id");
        // search employee by id
        Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);

        // check employee is present
        if(!employeeOpt.isPresent()){
            throw new NotFoundException("No employee exists with the given ID.");
        }

        if(!employeeOpt.get().getActive()){
            throw new NotFoundException("Employee no longer work here.");
        }

        log.info("fetched employee details ...");
        EmployeeModel employee = employeeOpt.get();
        EmployeeResponseDTO response = EmployeeModelToEmployeeResponseDTO.map(employee);

        // return the employee
        return response;
    }

    // method to get all employee
    public List<EmployeeResponseDTO> getAllEmployee() {
        List<EmployeeModel> activeEmployees = employeeRepo.findAllValidEmployee();
        return EmployeeMapper.mapToDTOList(activeEmployees);
    }

    // method to delete employee by id
    public EmployeeModel deleteEmployeeById( EmployeeModel validEmployee) throws Exception {

        // delete employee
        validEmployee.setActive(false);
        try {
            employeeRepo.save(validEmployee);
            return validEmployee;
        } catch (RuntimeException ex){
            throw new RuntimeException("An error occurred while deleting the employee: " + ex.getMessage());
        }

    }
}
