package com.example.calendarManagement.objectMapper;

import com.example.calendarManagement.dto.EmployeeRequestDTO;
import com.example.calendarManagement.dto.EmployeeResponseDTO;
import com.example.calendarManagement.model.EmployeeModel;

import java.util.List;
import java.util.stream.Collectors;


public class EmployeeMapper {

    public static EmployeeResponseDTO mapToDTO(EmployeeModel employeeModel) {
        return new EmployeeResponseDTO(
                employeeModel.getEmployeeId(),
                employeeModel.getEmployeeName(),
                employeeModel.getOffice().getOfficeId(),
                employeeModel.getEmployeeEmail(),
                employeeModel.getSalary(),
                employeeModel.getIsActive(),
                employeeModel.getDepartment()
        );
    }

    public static List<EmployeeResponseDTO> mapToDTOList(List<EmployeeModel> employeeModels) {
        return employeeModels.stream()
                .map(EmployeeMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
