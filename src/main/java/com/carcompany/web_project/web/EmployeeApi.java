package com.carcompany.web_project.web;

import com.carcompany.web_project.models.Employee;
import com.carcompany.web_project.models.dto.EmployeeDto;
import com.carcompany.web_project.service.EmployeeService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/employees", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class EmployeeApi {
    private final EmployeeService employeeService;

    public EmployeeApi(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> getAll(){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDto create(@RequestBody Employee employee) throws Exception {
        if (employee.getEmployee_id() != null && employeeService.exists(employee))
            throw new Exception("Employee already exists!");
        if (employee.getSaloon() == null)
            throw new Exception("Employee saloon invalid!");
        return employeeService.create(employee);
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) throws Exception {
        if (!employeeService.exists(id))
            throw new Exception("Employee doesn't exist!");
        return employeeService.get(id);
    }

    @DeleteMapping("/{id}/delete")
    public EmployeeDto deleteEmployee(@PathVariable Long id) throws Exception {
        if (!employeeService.exists(id))
            throw new Exception("Employee doesn't exist!");
        return employeeService.delete(id);
    }

    @PatchMapping("{id}/edit")
    public EmployeeDto edit(@PathVariable Long id,
                            @RequestParam String fname,
                            @RequestParam String lname) throws Exception {
        if(!employeeService.exists(id))
            throw new Exception("Employee doesn't exist!");
        return employeeService.edit(id, fname, lname);
    }

}
