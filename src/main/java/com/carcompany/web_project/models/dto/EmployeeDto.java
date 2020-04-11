package com.carcompany.web_project.models.dto;

import com.carcompany.web_project.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long employee_id;

    private String fname;

    private String lname;

    private SaloonDto saloon;

    private User user;

    private List<PurchaseDto> purchases;
}
