package com.example.calendarManagement.service;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.exception.InvalidFieldException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.objectMapper.EmployeeMapper;
import com.example.calendarManagement.objectMapper.EmployeeModelToEmployeeRequestDTO;
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
    public EmployeeModel addEmployee(EmployeeRequestDTO employee) {
        // checking missing input field
        if(employee.getEmployeeName()==null || employee.getEmployeeEmail()==null || employee.getDepartment()==null || employee.getOfficeID()==0 || employee.getSalary()==0){
            throw new MissingFieldException("Missing Required Input");
        }

        // checking valid employee email format
        if(!employee.getEmployeeEmail().trim().matches("^[A-Za-z0-9._%+-]+@xyz\\.com$")){
            throw new InvalidFieldException("Invalid Email Format");
        }

        Optional<OfficeModel> officeOpt = officeRepo.findById(employee.getOfficeID());
        if(!officeOpt.isPresent()){
            throw new NotFoundException("Office not found");
        }

        EmployeeModel newEmployee = new EmployeeModel(
                employee.getEmployeeName(),
                employee.getEmployeeEmail(),
                officeOpt.get(),
                employee.getDepartment(),
                employee.getActive(),
                employee.getSalary()
                 );

        EmployeeModel saveEmployee = employeeRepo.save(newEmployee);

        return saveEmployee;
    }

    // method to get employee by id
    public EmployeeRequestDTO getEmployeeById(int employeeId) {
        // search employee by id
        Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);

        // check employee is present
        if(!employeeOpt.isPresent() || !employeeOpt.get().getIsActive()){
            throw new NotFoundException("No employee exists with the given ID");
        }

        EmployeeModel employee = employeeOpt.get();
        EmployeeRequestDTO response = EmployeeModelToEmployeeRequestDTO.map(employee);

        // return the employee
        return response;
    }

    // method to get all employee
    public List<EmployeeRequestDTO> getAllEmployee() {
        List<EmployeeModel> activeEmployees = employeeRepo.findAllValidEmployee();
        return EmployeeMapper.mapToDTOList(activeEmployees);
    }

    // method to delete employee by id
    public EmployeeModel deleteEmployeeById(int employeeId) throws Exception {

        // search employee
        Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);

        // check employee is present
        if(!employeeOpt.isPresent()){
            throw new NotFoundException("Employee Does not Exit with Given ID");
        }

        // delete employee
        EmployeeModel updateEmployee = employeeOpt.get();
        updateEmployee.setActive(false);
        employeeRepo.save(updateEmployee);

        // return delete employee
        return updateEmployee;

    }
}
