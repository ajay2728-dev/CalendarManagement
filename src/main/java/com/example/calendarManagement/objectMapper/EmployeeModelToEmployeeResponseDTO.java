package com.example.calendarManagement.objectMapper;

import com.example.calendarManagement.dto.EmployeeResponseDTO;
import com.example.calendarManagement.model.EmployeeModel;
import org.springframework.stereotype.Component;

@Component
public class EmployeeModelToEmployeeResponseDTO {
    public EmployeeResponseDTO map(EmployeeModel employeeModel){
        return  new EmployeeResponseDTO( employeeModel.getEmployeeId(),employeeModel.getEmployeeName(),
                employeeModel.getOffice().getOfficeId(),
                employeeModel.getEmployeeEmail(),
                employeeModel.getSalary(),
                employeeModel.getIsActive(),
                employeeModel.getDepartment()
        );
    }
}
