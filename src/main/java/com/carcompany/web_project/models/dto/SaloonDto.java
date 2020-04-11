package com.carcompany.web_project.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaloonDto {

    private Long saloonID;

    private String city;

    private Integer carCapacity;

    private List<EmployeeDto> employees;

    private List<CarDto> cars;

}
