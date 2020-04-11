package com.carcompany.web_project.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {
    private Long purchase_id;

    private LocalDateTime date;

    private CarDto car;

    private EmployeeDto employee;

    private ClientDto client;
}
