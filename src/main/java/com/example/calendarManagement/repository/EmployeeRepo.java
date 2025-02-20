package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeModel,Integer> {
    Optional<EmployeeModel> findByEmployeeEmail(String employeeEmail);

    @Query(value = "SELECT * FROM employee_model em WHERE em.is_active = True", nativeQuery = true)
    List<EmployeeModel> findAllValidEmployee();

}