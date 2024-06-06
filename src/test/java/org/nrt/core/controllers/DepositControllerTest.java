package org.nrt.core.controllers;

import org.junit.jupiter.api.Test;
import org.nrt.core.controller.DepositController;
import org.nrt.core.model.Deposit;
import org.nrt.core.service.DepositService;
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

@WebMvcTest(DepositController.class)
public class DepositControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepositService depositService;

    @Test
    void testCreateDeposit() throws Exception{
        Deposit deposit = new Deposit();
        deposit.setDepositPercent("5.7");
        given(depositService.saveDeposit(any(Deposit.class))).willReturn(deposit);
        mockMvc.perform(post("/deposits")
                        .contentType("application/json")
                        .content("{\"depositPercent\":\"5.7\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.depositPercent").value("5.7"));
    }

    @Test
    void testGetOneDeposit() throws Exception{
        Deposit deposit = new Deposit();
        deposit.setDepositId(1);
        given(depositService.getOneDeposit(anyInt())).willReturn(Optional.of(deposit));
        mockMvc.perform(get("/deposits/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.depositId").value(1));
    }

    @Test
    void testGetAllDeposits() throws Exception{
        Pageable pageable = PageRequest.of(0, 10);
        given(depositService.getAllDeposits(any(Pageable.class))).willReturn(new PageImpl<>(Collections.singletonList(new Deposit())));
        mockMvc.perform(get("/deposits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testUpdateDeposit() throws Exception{
        Deposit deposit = new Deposit();
        deposit.setDepositId(1);
        deposit.setDepositPercent("9.3");
        given(depositService.updateDeposit(anyInt(), any(Deposit.class))).willReturn(deposit);
        mockMvc.perform(put("/deposits/1")
                        .contentType("application/json")
                        .content("{\"depositPercent\":\"9.3\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.depositPercent").value("9.3"));
    }

    @Test
    void testDeleteDeposit() throws Exception{
        mockMvc.perform(delete("/deposits/1"))
                .andExpect(status().isOk());
    }
}
