package com.carcompany.web_project.service;

import com.carcompany.web_project.models.Client;
import com.carcompany.web_project.models.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllClients();
    boolean exists(Client client);
    boolean exists(Long embg);
    ClientDto create(Client client);
    ClientDto edit(Long embg, String fname, String lname);
    ClientDto delete(Long embg);
    ClientDto get(Long embg);
}
