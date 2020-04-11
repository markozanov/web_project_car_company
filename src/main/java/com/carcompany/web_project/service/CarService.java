package com.carcompany.web_project.service;

import com.carcompany.web_project.models.Car;
import com.carcompany.web_project.models.VehicleType;
import com.carcompany.web_project.models.dto.CarDto;

import java.util.List;


public interface CarService {
    List<CarDto> getAllCars();
    boolean exists(Car car);
    boolean exists(String car_name);
    boolean exists(Long id);
    CarDto create(Car car);
    CarDto edit(Long car_id, String car_name, Integer year, Integer horsepower, Double engine_capacity, VehicleType type, String colour, Integer price, boolean sold);
    CarDto delete(Long id);
    CarDto get(String name);
    CarDto get(Long car_id);

}
