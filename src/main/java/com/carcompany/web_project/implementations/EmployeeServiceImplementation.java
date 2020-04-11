package com.carcompany.web_project.implementations;

import com.carcompany.web_project.models.Employee;
import com.carcompany.web_project.models.dto.EmployeeDto;
import com.carcompany.web_project.repos.EmployeeRepository;
import com.carcompany.web_project.repos.UserRepository;
import com.carcompany.web_project.service.EmployeeService;
import com.carcompany.web_project.utils.ModelsToDtoConverter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelsToDtoConverter dtoConverter;
    private final UserRepository userRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository, ModelsToDtoConverter dtoConverter, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.dtoConverter = dtoConverter;
        this.userRepository = userRepository;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> dto = employees.stream().map(dtoConverter::employeeToDto).collect(Collectors.toList());
        return dto;
    }

    @Override
    public boolean exists(Employee employee) {
        return this.exists(employee.getEmployee_id());
    }

    @Override
    public boolean exists(Long employee_id) {
        return this.employeeRepository.existsById(employee_id);
    }

    @Override
    @Transactional
    public EmployeeDto create(Employee employee) {
        employee.setUser(userRepository.save(employee.getUser()));
        return dtoConverter.employeeToDto(this.employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto delete(Long id) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
        if(!optionalEmployee.isPresent())
            return null;
        this.employeeRepository.delete(optionalEmployee.get());
        return dtoConverter.employeeToDto(optionalEmployee.get());
    }

    @Override
    public EmployeeDto edit(Long id, String fname, String lname) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
        if(!optionalEmployee.isPresent())
            return null;
        Employee newEmployee = optionalEmployee.get();
        newEmployee.setFname(fname);
        newEmployee.setLname(lname);
        return dtoConverter.employeeToDto(this.employeeRepository.save(newEmployee));
    }

    @Override
    public EmployeeDto get(Long id) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
        return optionalEmployee.map(dtoConverter::employeeToDto).orElse(null);
    }
}
