package org.nrt.core.controller;

import org.nrt.core.model.Client;
import org.nrt.core.model.Deposit;
import org.nrt.core.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/deposits")
public class DepositController {


    @Autowired
    private DepositService depositService;


    @PostMapping
    public Deposit createDeposit(@RequestBody Deposit deposit){
        return depositService.saveDeposit(deposit);
    }

    @GetMapping("/{id}")
    public Optional<Deposit> getOneDeposit(@PathVariable Integer id){
        return depositService.getOneDeposit(id);
    }

    @GetMapping
    public Page<Deposit> getAllDeposits(@RequestParam Optional<Integer> page,
                                        @RequestParam Optional<Integer> size,
                                        @RequestParam Optional<String> sortBy,
                                        @RequestParam Optional<String> sortDir){
        int pageNumber = page.orElse(0);
        int pageSize = size.orElse(10);
        String sortField = sortBy.orElse("depositId");
        Sort.Direction direction = Sort.Direction.fromString(sortDir.orElse("asc"));
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10), Sort.by(direction, sortField));
        return depositService.getAllDeposits(pageable);
    }

    @PutMapping("/{id}")
    public Deposit updateDeposit(@PathVariable Integer id, @RequestBody Deposit modifyDeposit){
        return depositService.updateDeposit(id, modifyDeposit);
    }

    @DeleteMapping("/{id}")
    public void deleteDeposit(@PathVariable Integer id){
        depositService.deleteDeposit(id);
    }
}
