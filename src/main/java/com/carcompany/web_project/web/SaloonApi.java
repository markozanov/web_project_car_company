package com.carcompany.web_project.web;


import com.carcompany.web_project.models.Saloon;
import com.carcompany.web_project.models.dto.SaloonDto;
import com.carcompany.web_project.service.SaloonService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/saloons", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class SaloonApi {
    private final SaloonService saloonService;

    public SaloonApi(SaloonService saloonService) {
        this.saloonService = saloonService;
    }

    @GetMapping
    public List<SaloonDto> getAll(){
        return saloonService.getAllSaloons();
    }

    @PostMapping
    public SaloonDto create(@RequestBody Saloon saloon) throws Exception {
        if(saloon.getSaloonID() != null && saloonService.exists(saloon))
            throw new Exception("Saloon already exists!");
        return saloonService.create(saloon);
    }

    @GetMapping("/{id}")
    public SaloonDto getById(@PathVariable Long id) throws Exception {
        if(!saloonService.exists(id))
            throw new Exception("Saloon doesn't exist!");
        return saloonService.get(id);
    }

    @DeleteMapping("/{id}/delete")
    public SaloonDto delete(@PathVariable Long id) throws Exception {
        if(!saloonService.exists(id))
            throw new Exception("Saloon doesn't exist!");
        return saloonService.delete(id);
    }

    @PatchMapping("/{id}/edit")
    public SaloonDto edit(@PathVariable Long id,
                       @RequestParam(required = false, defaultValue = "100") Integer capacity,
                       @RequestParam String city) throws Exception {
        if(!saloonService.exists(id))
            throw new Exception("Saloon doesn't exist!");
        return saloonService.edit(id, capacity, city);
    }
}
