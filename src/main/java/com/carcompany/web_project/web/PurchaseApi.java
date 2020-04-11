package com.carcompany.web_project.web;


import com.carcompany.web_project.models.Purchase;
import com.carcompany.web_project.models.dto.PurchaseDto;
import com.carcompany.web_project.service.PurchaseService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/purchases", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PurchaseApi {
    private final PurchaseService purchaseService;

    public PurchaseApi(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<PurchaseDto> getAll(){
        return purchaseService.getAllPurchases();
    }

    @PostMapping
    public PurchaseDto create(@RequestBody Purchase purchase) throws Exception {
        if(purchase.getPurchase_id() != null && purchaseService.exists(purchase))
            throw new Exception("Purchase already exist");
        return purchaseService.create(purchase);
    }

    @GetMapping("/{id}")
    public PurchaseDto getPurchaseById(@PathVariable Long id) throws Exception {
        if(!purchaseService.exists(id))
            throw new Exception("Purchase doesn't exist");
        return purchaseService.get(id);
    }

    @DeleteMapping("/{id}/delete")
    public PurchaseDto deletePurchase(@PathVariable Long id) throws Exception {
        if(!purchaseService.exists(id))
            throw new Exception("Purchase doesn't exist");
        return purchaseService.delete(id);
    }
}
