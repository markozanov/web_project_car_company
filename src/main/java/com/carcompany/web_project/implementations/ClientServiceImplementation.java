package com.carcompany.web_project.implementations;

import com.carcompany.web_project.models.Client;
import com.carcompany.web_project.models.dto.ClientDto;
import com.carcompany.web_project.repos.ClientRepository;
import com.carcompany.web_project.service.ClientService;
import com.carcompany.web_project.utils.ModelsToDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;
    private final ModelsToDtoConverter dtoConverter;

    public ClientServiceImplementation(ClientRepository clientRepository, ModelsToDtoConverter dtoConverter) {
        this.clientRepository = clientRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public List<ClientDto> getAllClients() {
        List<Client> clients =  this.clientRepository.findAll();
        return clients.stream().map(dtoConverter::clientToDto).collect(Collectors.toList());
    }

    @Override
    public boolean exists(Long embg) {
        return this.clientRepository.existsById(embg);
    }

    @Override
    public boolean exists(Client client) {
        return this.clientRepository.existsById(client.getEmbg());
    }

    @Override
    public ClientDto create(Client client) {
        return dtoConverter.clientToDto(this.clientRepository.save(client));

    }

    @Override
    public ClientDto edit(Long embg, String fname, String lname) {
        Optional<Client> optionalClient = this.clientRepository.findById(embg);
        if(!optionalClient.isPresent())
            return null;
        Client newClient = optionalClient.get();
        newClient.setFname(fname);
        newClient.setLname(lname);
        return dtoConverter.clientToDto(this.clientRepository.save(newClient));
    }

    @Override
    public ClientDto delete(Long client_id) {
        Optional<Client> optionalClient = this.clientRepository.findById(client_id);
        if(!optionalClient.isPresent())
            return null;
        this.clientRepository.delete(optionalClient.get());
        return dtoConverter.clientToDto(optionalClient.get());
    }

    @Override
    public ClientDto get(Long client_id) {
        Optional<Client> optionalClient = this.clientRepository.findById(client_id);
        return optionalClient.map(dtoConverter::clientToDto).orElse(null);
    }

}
