package org.nrt.core.service;

import org.nrt.core.model.Bank;
import org.nrt.core.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public Page<Bank> getAllBanks(Pageable pageable){
        return bankRepository.findAll(pageable);
    }

    public Optional<Bank> getOneBank(Integer id){
        return bankRepository.findById(id);
    }

    public Bank saveBank(Bank bank){
        return bankRepository.save(bank);
    }

    public Bank updateBank(Integer id, Bank modifyBank){
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            Bank currentBank = optionalBank.get();
            currentBank.setBankName(modifyBank.getBankName());
            currentBank.setBankBic(modifyBank.getBankBic());
            return bankRepository.save(currentBank);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Банк по id " + id + " не найден.");
        }
    }

    public void deleteBank(Integer id){
        bankRepository.deleteById(id);
    }
}
