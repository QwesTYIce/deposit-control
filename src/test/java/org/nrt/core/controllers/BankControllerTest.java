package org.nrt.core.controllers;

import org.junit.jupiter.api.Test;
import org.nrt.core.controller.BankController;
import org.nrt.core.model.Bank;
import org.nrt.core.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankController.class)
public class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @Test
    void testCreateBank() throws Exception{
        Bank bank = new Bank();
        bank.setBankName("Bank Test Name");
        given(bankService.saveBank(any(Bank.class))).willReturn(bank);
        mockMvc.perform(post("/banks")
                        .contentType("application/json")
                        .content("{\"bankName\":\"Bank Test Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bankName").value("Bank Test Name"));
    }

    @Test
    void testGetOneBank() throws Exception{
        Bank bank = new Bank();
        bank.setBankId(1);
        given(bankService.getOneBank(anyInt())).willReturn(Optional.of(bank));
        mockMvc.perform(get("/banks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bankId").value(1));
    }

    @Test
    void testGetAllBanks() throws Exception{
        Pageable pageable = PageRequest.of(0, 10);
        given(bankService.getAllBanks(any(Pageable.class))).willReturn(new PageImpl<>(Collections.singletonList(new Bank())));
        mockMvc.perform(get("/banks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testUpdateBank() throws Exception{
        Bank bank = new Bank();
        bank.setBankId(1);
        bank.setBankName("Update Alphach Name");
        given(bankService.updateBank(anyInt(), any(Bank.class))).willReturn(bank);
        mockMvc.perform(put("/banks/1")
                        .contentType("application/json")
                        .content("{\"bankName\":\"Update Alphach Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bankName").value("Update Alphach Name"));
    }

    @Test
    void testDeleteBank() throws Exception{
        mockMvc.perform(delete("/banks/1"))
                .andExpect(status().isOk());
    }
}
