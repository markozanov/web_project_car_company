package com.carcompany.web_project.repos;

import com.carcompany.web_project.models.Saloon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaloonRepository extends JpaRepository<Saloon, Long> {

     boolean existsByCity(String city);
}
