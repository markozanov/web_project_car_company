package com.carcompany.web_project.repos;

import com.carcompany.web_project.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
