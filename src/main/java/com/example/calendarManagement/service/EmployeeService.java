package com.example.calendarManagement.service;

import com.example.calendarManagement.exception.EmployeeInvalidEmailException;
import com.example.calendarManagement.exception.EmployeeMissingInputException;
import com.example.calendarManagement.exception.NonUniqueEmployeeEmailException;
import com.example.calendarManagement.exception.NotFoundEmployeeException;
import com.example.calendarManagement.model.EmployeeModel;
import com.example.calendarManagement.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public EmployeeModel addEmployee(EmployeeModel employee) {
        // checking missing input field
        if(employee.getEmployeeName()==null || employee.getEmployeeEmail()==null || employee.getDepartment()==null || employee.getOfficeLocation()==null || employee.getSalary()==0){
            throw new EmployeeMissingInputException("Missing Required Input");
        }

        // checking valid employee email format
        if(!employee.getEmployeeEmail().trim().matches("^[A-Za-z0-9._%+-]+@xyz\\.com$")){
            throw new EmployeeInvalidEmailException("Invalid Email Format");
        }

        // check unique email
        String emp_email=employee.getEmployeeEmail();
        if(employeeRepo.findByEmployeeEmail(emp_email).isPresent()){
            throw new NonUniqueEmployeeEmailException("Provide Different Employee Email");
        }

        int generateEmployeeId=(int) (employeeRepo.count() + 1);

        EmployeeModel newEmployee = new EmployeeModel(
                generateEmployeeId,
                employee.getEmployeeName(),
                employee.getEmployeeEmail(),
                employee.getOfficeLocation(),
                employee.getActive(),
                employee.getSalary(),
                employee.getDepartment() );

        EmployeeModel saveEmployee = employeeRepo.save(newEmployee);

        return saveEmployee;
    }

    public EmployeeModel getEmployeeById(int employeeId) {
        // search employee by id
        Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);

        // check employee is present
        if(!employeeOpt.isPresent()){
            throw new NotFoundEmployeeException("Employee Not Found");
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
}
