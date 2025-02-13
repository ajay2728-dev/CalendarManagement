package com.example.calendarManagement.service;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.exception.InvalidFieldException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NonUniqueFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.repository.EmployeeRepo;
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

    // method to add employee
    public EmployeeModel addEmployee(EmployeeRequestDTO employee) {
        // checking missing input field
        if(employee.getEmployeeName()==null || employee.getEmployeeEmail()==null || employee.getDepartment()==null || employee.getOfficeLocation()==null || employee.getSalary()==0){
            throw new MissingFieldException("Missing Required Input");
        }

        // checking valid employee email format
        if(!employee.getEmployeeEmail().trim().matches("^[A-Za-z0-9._%+-]+@xyz\\.com$")){
            throw new InvalidFieldException("Invalid Email Format");
        }

        // check unique email
        String emp_email=employee.getEmployeeEmail();
        if(employeeRepo.findByEmployeeEmail(emp_email).isPresent()){
            throw new NonUniqueFieldException("Provide Different Employee Email");
        }

        int generateEmployeeId=(int) (employeeRepo.count() + 1);

        EmployeeModel newEmployee = new EmployeeModel(
                generateEmployeeId,
                employee.getEmployeeName(),
                employee.getOfficeLocation(),
                employee.getEmployeeEmail(),
                employee.getActive(),
                employee.getSalary(),
                employee.getDepartment() );

        EmployeeModel saveEmployee = employeeRepo.save(newEmployee);

        return saveEmployee;
    }

    // method to get employee by id
    public EmployeeModel getEmployeeById(int employeeId) {
        // search employee by id
        Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);

        // check employee is present
        if(!employeeOpt.isPresent()){
            throw new NotFoundException("No employee exists with the given ID");
        }

        EmployeeModel employee = employeeOpt.get();

        // return the employee
        return new EmployeeModel(employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getOfficeLocation(),
                employee.getEmployeeEmail(),
                employee.getActive(),
                employee.getSalary(),
                employee.getDepartment() );
    }

    // method to get all empployee
    public List<EmployeeModel> getAllEmployee() {
        return employeeRepo.findAll();
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
