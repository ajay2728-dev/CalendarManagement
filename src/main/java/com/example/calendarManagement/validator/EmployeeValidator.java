package com.example.calendarManagement.validator;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.exception.ConstraintViolationException;
import com.example.calendarManagement.exception.InvalidFieldException;
import com.example.calendarManagement.exception.MissingFieldException;
import com.example.calendarManagement.exception.NotFoundException;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.model.OfficeModel;
import com.example.calendarManagement.repository.EmployeeRepo;
import com.example.calendarManagement.repository.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeValidator {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private OfficeRepo officeRepo;

    public OfficeModel addEmployeeValidator(EmployeeRequestDTO employee){
        // checking missing input field
        if(employee.getEmployeeName()==null || employee.getEmployeeEmail()==null || employee.getDepartment()==null || employee.getOfficeID()==0 || employee.getSalary()==0){
            throw new MissingFieldException("Missing Required Input");
        }

        // checking valid employee email format
        if(!employee.getEmployeeEmail().trim().matches("^[A-Za-z0-9._%+-]+@xyz\\.com$")){
            throw new InvalidFieldException("Invalid Email Format");
        }

        // checking unique employee email
        if(employeeRepo.findByEmployeeEmail(employee.getEmployeeEmail()).isPresent()){
            throw new ConstraintViolationException("Employee with given email already existing.");
        }

        Optional<OfficeModel> officeOpt = officeRepo.findById(employee.getOfficeID());
        if(!officeOpt.isPresent()){
            throw new NotFoundException("Office not found");
        }

        return officeOpt.get();

    }

    public EmployeeModel deleteEmployeeValidator( int employeeId ){
        // search employee
        Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);

        // check employee is present
        if(!employeeOpt.isPresent()){
            throw new NotFoundException("Employee Does not Exit with Given ID");
        }

        if(!employeeOpt.get().getActive()){
            throw new NotFoundException("Employee no longer work here");
        }

        return employeeOpt.get();
    }
}
