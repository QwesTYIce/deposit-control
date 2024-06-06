package org.nrt.core.service;

import org.nrt.core.model.Bank;
import org.nrt.core.model.Client;
import org.nrt.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Page<Client> getAllClients(Pageable pageable){
        return clientRepository.findAll(pageable);
    }

    public Optional<Client> getOneClient(Integer id){
        return clientRepository.findById(id);
    }
    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    public Client updateClient(Integer id, Client clientModify){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client currentClient = optionalClient.get();
            currentClient.setClientName(clientModify.getClientName());
            currentClient.setClientShortname(clientModify.getClientShortname());
            currentClient.setClientAddress(clientModify.getClientAddress());
            currentClient.setClientLegalForm(clientModify.getClientLegalForm());
            return clientRepository.save(currentClient);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Клиент по id " + id + " не найден.");
        }
    }

    public void deleteCLient(Integer id){
        clientRepository.deleteById(id);
    }
}
