package com.carcompany.web_project.repos;

import com.carcompany.web_project.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {


    Optional<Car> findByName(String name);

    boolean existsByName(String car_name);
}
