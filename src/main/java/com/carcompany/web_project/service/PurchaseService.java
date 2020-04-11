package com.carcompany.web_project.service;

import com.carcompany.web_project.models.Purchase;
import com.carcompany.web_project.models.dto.PurchaseDto;

import java.util.List;

public interface PurchaseService {
    List<PurchaseDto> getAllPurchases();
    boolean exists(Purchase purchase);
    boolean exists(Long purchase_id);
    PurchaseDto create(Purchase purchase);
    PurchaseDto delete(Long purchase_id);
    PurchaseDto get(Long purchase_id);
}
