package com.carcompany.web_project.implementations;

import com.carcompany.web_project.models.Saloon;
import com.carcompany.web_project.models.dto.SaloonDto;
import com.carcompany.web_project.repos.SaloonRepository;
import com.carcompany.web_project.service.SaloonService;
import com.carcompany.web_project.utils.ModelsToDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaloonServiceImplementation implements SaloonService {

    private final SaloonRepository saloonRepository;
    private final ModelsToDtoConverter dtoConverter;

    public SaloonServiceImplementation(SaloonRepository saloonRepository, ModelsToDtoConverter dtoConverter) {
        this.saloonRepository = saloonRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public List<SaloonDto> getAllSaloons() {
        List<Saloon> saloons = this.saloonRepository.findAll();
        List<SaloonDto> dto = saloons.stream().map(dtoConverter::saloonToDto).collect(Collectors.toList());
        return dto;
    }

    @Override
    public boolean exists(Saloon saloon) {
        return this.saloonRepository.existsById(saloon.getSaloonID());
    }

    @Override
    public boolean exists(Long id) {
        return this.saloonRepository.existsById(id);
    }

    @Override
    public boolean exists(String city) {
        return this.saloonRepository.existsByCity(city);
    }

    @Override
    public SaloonDto create(Saloon saloon) {
        return dtoConverter.saloonToDto(this.saloonRepository.save(saloon));
    }

    @Override
    public SaloonDto edit(Long saloon_id, Integer car_capacity, String city) {
        Optional<Saloon> optionalSaloon = this.saloonRepository.findById(saloon_id);
        if(!optionalSaloon.isPresent())
            return null;
        Saloon newSaloon = optionalSaloon.get();
        newSaloon.setCarCapacity(car_capacity);
        newSaloon.setCity(city);
        return dtoConverter.saloonToDto(this.saloonRepository.save(newSaloon));
    }

    @Override
    public SaloonDto delete(Long saloon_id) {
        Optional<Saloon> optionalSaloon = this.saloonRepository.findById(saloon_id);
        if(!optionalSaloon.isPresent())
            return null;
        this.saloonRepository.delete(optionalSaloon.get());
        return dtoConverter.saloonToDto(optionalSaloon.get());
    }

    @Override
    public SaloonDto get(Long saloon_id) {
        Optional<Saloon> optSaloon = this.saloonRepository.findById(saloon_id);
        return optSaloon.map(dtoConverter::saloonToDto).orElse(null);
    }
}
