package org.nrt.core.service;

import org.nrt.core.model.Deposit;
import org.nrt.core.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    public Page<Deposit> getAllDeposits(Pageable pageable){
        return depositRepository.findAll(pageable);
    }

    public Optional<Deposit> getOneDeposit(Integer id){
        return depositRepository.findById(id);
    }

    public Deposit saveDeposit(Deposit deposit){
        return depositRepository.save(deposit);
    }

    public Deposit updateDeposit(Integer id, Deposit modifyDeposit){
        Optional<Deposit> optionalDeposit = depositRepository.findById(id);
        if(optionalDeposit.isPresent()){
            Deposit currentDeposit = optionalDeposit.get();
            currentDeposit.setClient(modifyDeposit.getClient());
            currentDeposit.setBank(modifyDeposit.getBank());
            currentDeposit.setDepositFirstdate(modifyDeposit.getDepositFirstdate());
            currentDeposit.setDepositPercent(modifyDeposit.getDepositPercent());
            currentDeposit.setDepositTermInMonths(modifyDeposit.getDepositTermInMonths());
            return depositRepository.save(modifyDeposit);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Депозит по id " + id + " не найден.");
        }
    }
    public void deleteDeposit(Integer id){
        depositRepository.deleteById(id);
    }

}
