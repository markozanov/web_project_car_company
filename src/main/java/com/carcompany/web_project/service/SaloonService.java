package com.carcompany.web_project.service;

import com.carcompany.web_project.models.Saloon;
import com.carcompany.web_project.models.dto.SaloonDto;

import java.util.List;

public interface SaloonService {
    List<SaloonDto> getAllSaloons();
    boolean exists(Saloon saloon);
    boolean exists(Long id);
    boolean exists(String city);
    SaloonDto create(Saloon saloon);
    SaloonDto edit(Long saloon_id, Integer car_capacity, String city);
    SaloonDto delete(Long saloon_id);
    SaloonDto get(Long saloon_id);

}
