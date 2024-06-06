package org.nrt.core.controller;

import org.nrt.core.model.Bank;
import org.nrt.core.model.Client;
import org.nrt.core.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping
    public Bank createBank(@RequestBody Bank bank){
        return bankService.saveBank(bank);
    }

    @GetMapping("/{id}")
    public Optional<Bank> getOneBank(@PathVariable Integer id){
        return bankService.getOneBank(id);
    }

    @GetMapping
    public Page<Bank> getAllBanks(@RequestParam Optional<Integer> page,
                                  @RequestParam Optional<Integer> size,
                                  @RequestParam Optional<String> sortBy,
                                  @RequestParam Optional<String> sortDir){
        int pageNumber = page.orElse(0);
        int pageSize = size.orElse(10);
        String sortField = sortBy.orElse("bankId");
        Sort.Direction direction = Sort.Direction.fromString(sortDir.orElse("asc"));
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10), Sort.by(direction, sortField));
        return bankService.getAllBanks(pageable);
    }

    @PutMapping("/{id}")
    public Bank updateBank(@PathVariable Integer id, @RequestBody Bank modifyBank){
        return bankService.updateBank(id, modifyBank);
    }

    @DeleteMapping("/{id}")
    public void deleteBank(@PathVariable Integer id){
        bankService.deleteBank(id);
    }
}
