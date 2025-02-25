package com.example.calendarManagement.objectMapper;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.dto.EmployeeResponseDTO;
import com.example.calendarManagement.model.EmployeeModel;

public class EmployeeModelToEmployeeRequestDTO {
    public static EmployeeRequestDTO map(EmployeeModel employeeModel){
        return  new EmployeeRequestDTO( employeeModel.getEmployeeId(),employeeModel.getEmployeeName(),
                employeeModel.getOffice().getOfficeId(),
                employeeModel.getEmployeeEmail(),
                employeeModel.getIsActive(),
                employeeModel.getSalary(),
                employeeModel.getDepartment()
        );
    }
}
