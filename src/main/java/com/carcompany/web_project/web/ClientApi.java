package com.carcompany.web_project.web;

import com.carcompany.web_project.models.Client;
import com.carcompany.web_project.models.dto.ClientDto;
import com.carcompany.web_project.service.ClientService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/clients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class ClientApi {
    private final ClientService clientService;

    public ClientApi(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDto> getAll() {
        return clientService.getAllClients();
    }

    @PostMapping
    public ClientDto create(@RequestBody Client client) throws Exception {
        if (client.getEmbg() != null && clientService.exists(client))
            return null;
        return clientService.create(client);
    }

    @GetMapping("/{embg}")
    public ClientDto getClientByEmbg(@PathVariable Long embg) throws Exception {
        if(!clientService.exists(embg))
            throw new Exception("Client doesn't exist");
        return clientService.get(embg);
    }

    @DeleteMapping("/{embg}/delete")
    public ClientDto deleteClient(@PathVariable Long embg) throws Exception {
        if(!clientService.exists(embg))
            throw new Exception("Client doesn't exist");
        return clientService.delete(embg);
    }

    @PatchMapping("/{embg}/edit")
    public ClientDto editClient(@PathVariable Long embg,
                                @RequestParam String fname,
                                @RequestParam String lname) throws Exception {
        if(!clientService.exists(embg))
            throw new Exception("Client doesn't exist");
        return clientService.edit(embg, fname, lname);
    }
}
