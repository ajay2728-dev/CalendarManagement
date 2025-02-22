package com.example.calendarManagement.objectMapper;

import com.example.calendarManagement.dto.EmployeeResponseDTO;
import com.example.calendarManagement.model.EmployeeModel;

public class EmployeeModelToEmployeeResponseDTO {
    public static EmployeeResponseDTO map(EmployeeModel employeeModel){
        return  new EmployeeResponseDTO( employeeModel.getEmployeeId(),employeeModel.getEmployeeName(),
                employeeModel.getOffice().getOfficeId(),
                employeeModel.getEmployeeEmail(),
                employeeModel.getSalary(),
                employeeModel.getActive(),
                employeeModel.getDepartment()
        );
    }
}
