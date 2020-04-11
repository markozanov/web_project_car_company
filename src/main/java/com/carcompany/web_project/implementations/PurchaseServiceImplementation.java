package com.carcompany.web_project.implementations;

import com.carcompany.web_project.models.Car;
import com.carcompany.web_project.models.Purchase;
import com.carcompany.web_project.models.dto.PurchaseDto;
import com.carcompany.web_project.repos.CarRepository;
import com.carcompany.web_project.repos.ClientRepository;
import com.carcompany.web_project.repos.PurchaseRepository;
import com.carcompany.web_project.service.CarService;
import com.carcompany.web_project.service.PurchaseService;
import com.carcompany.web_project.utils.ModelsToDtoConverter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImplementation implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final ModelsToDtoConverter dtoConverter;
    private final CarService carService;

    public PurchaseServiceImplementation(PurchaseRepository purchaseRepository, ClientRepository clientRepository, CarRepository carRepository, ModelsToDtoConverter dtoConverter, CarService carService) {
        this.purchaseRepository = purchaseRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
        this.dtoConverter = dtoConverter;
        this.carService = carService;
    }


    @Override
    public List<PurchaseDto> getAllPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream().map(dtoConverter::purchaseToDto).collect(Collectors.toList());
    }

    @Override
    public boolean exists(Purchase purchase) {
        return exists(purchase.getPurchase_id());
    }

    @Override
    public boolean exists(Long purchase_id) {
        return purchaseRepository.existsById(purchase_id);
    }

    @Override
    @Transactional
    public PurchaseDto create(Purchase purchase) {
        purchase.setClient(clientRepository.save(purchase.getClient()));

        Car toEdit = purchase.getCar();
        carService.edit(toEdit.getCar_id(), toEdit.getName(), toEdit.getYear(), toEdit.getHorsepower(), toEdit.getEnginecapacity(),
                toEdit.getVehicletype(), toEdit.getColour(), toEdit.getPrice(), true);
        Car car = carRepository.findById(purchase.getCar().getCar_id()).get();
        purchase.setCar(car);

        purchase.setDate(LocalDateTime.now());

        return dtoConverter.purchaseToDto(purchaseRepository.save(purchase));
    }

    @Override
    public PurchaseDto delete(Long purchase_id) {
        Optional<Purchase> optPurchase = purchaseRepository.findById(purchase_id);
        if(!optPurchase.isPresent())
            return null;
        purchaseRepository.delete(optPurchase.get());
        return dtoConverter.purchaseToDto(optPurchase.get());
    }

    @Override
    public PurchaseDto get(Long purchase_id) {
        Optional<Purchase> optPurchase = purchaseRepository.findById(purchase_id);
        if(!optPurchase.isPresent())
            return null;
        return optPurchase.map(dtoConverter::purchaseToDto).orElse(null);
    }
}
