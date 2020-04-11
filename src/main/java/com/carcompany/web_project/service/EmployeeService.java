package com.carcompany.web_project.service;

import com.carcompany.web_project.models.Employee;
import com.carcompany.web_project.models.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();
    boolean exists(Employee employee);
    boolean exists(Long employee_id);
    EmployeeDto create(Employee employee);
    EmployeeDto delete(Long id);
    EmployeeDto edit(Long id, String fname, String lname);
    EmployeeDto get(Long id);

}
