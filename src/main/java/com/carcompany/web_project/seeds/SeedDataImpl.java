package com.carcompany.web_project.seeds;

import com.carcompany.web_project.models.Saloon;
import com.carcompany.web_project.repos.EmployeeRepository;
import com.carcompany.web_project.repos.SaloonRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class SeedDataImpl implements SeedData {

    private final SaloonRepository saloonRepository;

    public SeedDataImpl(SaloonRepository saloonRepository){
        this.saloonRepository = saloonRepository;
    }

    @Override
    public void seedSaloonData() {
        if (saloonRepository.count() == 0) {
            Saloon saloon = new Saloon();
            saloon.setCarCapacity(100);
            saloon.setCity("Skopje");
            saloonRepository.save(saloon);
        }
    }



}
