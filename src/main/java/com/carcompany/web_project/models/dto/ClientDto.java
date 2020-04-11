package com.carcompany.web_project.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Long embg;

    private String fname;

    private String lname;

    private List<PurchaseDto> purchases;
}
