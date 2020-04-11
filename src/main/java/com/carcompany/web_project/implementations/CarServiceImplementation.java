package com.carcompany.web_project.implementations;

import com.carcompany.web_project.models.Car;
import com.carcompany.web_project.models.VehicleType;
import com.carcompany.web_project.models.dto.CarDto;
import com.carcompany.web_project.repos.CarRepository;
import com.carcompany.web_project.service.CarService;
import com.carcompany.web_project.utils.ModelsToDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImplementation implements CarService {

    private final CarRepository carRepository;
    private final ModelsToDtoConverter dtoConverter;

    public CarServiceImplementation(CarRepository carRepository, ModelsToDtoConverter dtoConverter) {
        this.carRepository = carRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDto> dto = cars.stream().map(dtoConverter::carToDto).collect(Collectors.toList());
        return dto;
    }

    @Override
    public boolean exists(Car car) {
        return this.carRepository.existsById(car.getCar_id());
    }

    @Override
    public boolean exists(Long id) {
        return this.carRepository.existsById(id);
    }

    @Override
    public boolean exists(String car_name) {
        return this.carRepository.existsByName(car_name);
    }

    @Override
    public CarDto create(Car car) {
        return dtoConverter.carToDto(this.carRepository.save(car));
    }

    @Override
    public CarDto edit(Long car_id, String car_name, Integer year, Integer horsepower, Double engine_capacity,
                       VehicleType type, String colour, Integer price, boolean sold) {
        Optional<Car> car = this.carRepository.findById(car_id);
        if(!car.isPresent())
            return null;

        Car newCar = car.get();
        newCar.setName(car_name);
        newCar.setYear(year);
        newCar.setHorsepower(horsepower);
        newCar.setColour(colour);
        newCar.setVehicletype(type);
        newCar.setEnginecapacity(engine_capacity);
        newCar.setPrice(price);
        newCar.setSold(sold);
        return dtoConverter.carToDto(this.carRepository.save(newCar));
    }

    @Override
    public CarDto delete(Long car_id) {
        Optional<Car> car = this.carRepository.findById(car_id);
        if(!car.isPresent())
            return null;
        this.carRepository.delete(car.get());
        return dtoConverter.carToDto(car.get());
    }


    @Override
    public CarDto get(String name) {
        Optional<Car> car = this.carRepository.findByName(name);
        return car.map(dtoConverter::carToDto).orElse(null);
    }

    @Override
    public CarDto get(Long car_id) {
        Optional<Car> car = this.carRepository.findById(car_id);
        return car.map(dtoConverter::carToDto).orElse(null);
    }
}
