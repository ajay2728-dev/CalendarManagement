package com.example.calendarManagement.objectMapper;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.model.EmployeeModel;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {

    public static EmployeeRequestDTO mapToDTO(EmployeeModel employeeModel) {
        return new EmployeeRequestDTO(
                employeeModel.getEmployeeId(),
                employeeModel.getEmployeeName(),
                employeeModel.getOffice().getOfficeId(),
                employeeModel.getEmployeeEmail(),
                employeeModel.getActive(),
                employeeModel.getSalary(),
                employeeModel.getDepartment()
        );
    }

    public static List<EmployeeRequestDTO> mapToDTOList(List<EmployeeModel> employeeModels) {
        return employeeModels.stream()
                .map(EmployeeMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
