package com.carcompany.web_project.models.dto;

import com.carcompany.web_project.models.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Long car_id;

    private String name;

    private Integer year;

    private Integer horsepower;

    private Double enginecapacity;

    private VehicleType vehicletype;

    private String colour;

    private Integer price;

    private boolean sold;

    private SaloonDto saloon;
}
