package org.nrt.core.controller;

import org.nrt.core.model.Client;
import org.nrt.core.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public Client createClient(@RequestBody Client client){
        return clientService.saveClient(client);
    }

    @GetMapping("/{id}")
    public Optional<Client> getOneClient(@PathVariable Integer id){
        return clientService.getOneClient(id);
    }

    @GetMapping
    public Page<Client> getAllClients(@RequestParam Optional<Integer> page,
                                      @RequestParam Optional<Integer> size,
                                      @RequestParam Optional<String> sortBy,
                                      @RequestParam Optional<String> sortDir){
        int pageNumber = page.orElse(0);
        int pageSize = size.orElse(10);
        String sortField = sortBy.orElse("clientId");
        Sort.Direction direction = Sort.Direction.fromString(sortDir.orElse("asc"));
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10), Sort.by(direction, sortField));
        return clientService.getAllClients(pageable);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Integer id, @RequestBody Client modifyClient){
        return clientService.updateClient(id, modifyClient);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id){
        clientService.deleteCLient(id);
    }

}
