package com.carcompany.web_project.utils;

import com.carcompany.web_project.models.*;
import com.carcompany.web_project.models.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelsToDtoConverter {
    private final ModelMapper modelMapper;


    public ModelsToDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CarDto carToDto(Car car){
        CarDto carDto = modelMapper.map(car, CarDto.class);
        carDto.setSaloon(saloonToDto(car.getSaloon()));
        return carDto;
    }

    public CarDto saloonCarToDto(Car car) {
        CarDto carDto = modelMapper.map(car, CarDto.class);
        carDto.setSaloon(null);
        return carDto;
    }



    public SaloonDto saloonToDto(Saloon saloon) {
        SaloonDto saloonDto = modelMapper.map(saloon, SaloonDto.class);
        List<CarDto> carsDtos = new ArrayList<>();
        List<EmployeeDto> employeesDtos = new ArrayList<>();

        if (saloon.getCars() != null)
            carsDtos = saloon.getCars().stream().map(this::saloonCarToDto).collect(Collectors.toList());

        if(saloon.getEmployees() != null)
            employeesDtos = saloon.getEmployees().stream().map(this::saloonEmployeeToDto).collect(Collectors.toList());

        saloonDto.setCars(carsDtos);
        saloonDto.setEmployees(employeesDtos);
        return saloonDto;
    }



    public EmployeeDto employeeToDto(Employee employee){
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        dto.setSaloon(saloonToDto(employee.getSaloon()));
        List<PurchaseDto> purchaseDtos = new ArrayList<>();
        if(employee.getPurchases()!=null)
            purchaseDtos = employee.getPurchases().stream().map(this::employeePurchaseToDto).collect(Collectors.toList());
        dto.setPurchases(purchaseDtos);
        return dto;
    }

    public EmployeeDto purchaseEmployeeToDto(Employee employee){
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        dto.setSaloon(saloonToDto(employee.getSaloon()));
        dto.getSaloon().setEmployees(null);
        dto.getSaloon().setCars(null);
        dto.setPurchases(null);
        return dto;
    }

    public EmployeeDto saloonEmployeeToDto(Employee employee){
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        dto.setSaloon(null);
        dto.setPurchases(employee.getPurchases().stream().map(this::employeePurchaseToDto).collect(Collectors.toList()));
        return dto;
    }



    public PurchaseDto employeePurchaseToDto(Purchase purchase){
        PurchaseDto purchaseDto = modelMapper.map(purchase, PurchaseDto.class);
        purchaseDto.setEmployee(null);
        purchaseDto.getCar().getSaloon().setEmployees(null);
        purchaseDto.getCar().getSaloon().setCars(null);
        purchaseDto.getClient().setPurchases(null);
        return purchaseDto;
    }

    public PurchaseDto clientPurchaseToDto(Purchase purchase){
        PurchaseDto purchaseDto = modelMapper.map(purchase, PurchaseDto.class);
        purchaseDto.setClient(null);
        purchaseDto.getEmployee().setSaloon(null);
        purchaseDto.getEmployee().setPurchases(null);
        purchaseDto.getCar().getSaloon().setCars(null);
        purchaseDto.getCar().getSaloon().setEmployees(null);
        return purchaseDto;
    }

    public PurchaseDto purchaseToDto(Purchase purchase){
        PurchaseDto purchaseDto = modelMapper.map(purchase, PurchaseDto.class);

        purchaseDto.getCar().getSaloon().setCars(null);
        purchaseDto.getCar().getSaloon().setEmployees(null);

        purchase.getEmployee().setSaloon(purchase.getCar().getSaloon());
        purchaseDto.setEmployee(purchaseEmployeeToDto(purchase.getEmployee()));

        purchaseDto.setClient(purchaseClientToDto(purchase.getClient()));

        return purchaseDto;
    }



    public ClientDto clientToDto(Client client){
        ClientDto clientDto = modelMapper.map(client, ClientDto.class);
        List<PurchaseDto> purchaseDtos = new ArrayList<>();
        if(client.getPurchases() != null)
            purchaseDtos = client.getPurchases().stream().map(this::clientPurchaseToDto).collect(Collectors.toList());
        clientDto.setPurchases(purchaseDtos);
        return clientDto;
    }

    public ClientDto purchaseClientToDto(Client client){
        ClientDto clientDto = modelMapper.map(client, ClientDto.class);
        clientDto.setPurchases(null);
        return clientDto;
    }
}
