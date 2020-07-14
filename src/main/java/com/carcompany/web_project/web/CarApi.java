package com.carcompany.web_project.web;


import com.carcompany.web_project.models.Car;
import com.carcompany.web_project.models.VehicleType;
import com.carcompany.web_project.models.dto.CarDto;
import com.carcompany.web_project.service.CarService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "*"})
@RequestMapping(path = "/cars", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class CarApi {
    private final CarService carService;

    public CarApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> getAll(){
        return carService.getAllCars();
    }

    @PostMapping
    public CarDto create(@RequestBody Car car) throws Exception {
        if(car.getCar_id()!=null  && carService.exists(car))
            throw new Exception("Car already exists");
        if (car.getSaloon() == null) {
            throw new Exception("Car saloon invalid");
        }
        return carService.create(car);
    }

    @GetMapping("/name/{name}")
    public CarDto getByName(@PathVariable String name) throws Exception {
        if(!carService.exists(name))
            throw new Exception("Car doesn't exist!");
        return carService.get(name);
    }

    @GetMapping("/{id}")
    public CarDto getByID(@PathVariable Long id) throws Exception {
        if(!carService.exists(id))
            throw new Exception("Car doesn't exist!");
        return carService.get(id);
    }

    @DeleteMapping("/{id}/delete")
    public CarDto delete(@PathVariable Long id) throws Exception {
        if(!carService.exists(id)){
            throw new Exception("Emplpyee doesn't exist!");
        }

        return carService.delete(id);
    }

    @PatchMapping("/{id}/edit")
    public CarDto edit(@PathVariable Long id,
                    @RequestParam String name,
                    @RequestParam Double enginecapacity,
                    @RequestParam Integer horsepower,
                    @RequestParam Integer year,
                    @RequestParam(required = false, defaultValue = "black") String colour,
                    @RequestParam(required = false, defaultValue = "15000") Integer price,
                    @RequestParam(required = false, defaultValue = "SEDAN") VehicleType vehicletype,
                    @RequestParam(required = false, defaultValue = "false") boolean sold   ) throws Exception {

        if(!carService.exists(id))
            throw new Exception("Car doesn't exist");
        return carService.edit(id, name, year, horsepower, enginecapacity, vehicletype, colour, price, sold);
    }
}
