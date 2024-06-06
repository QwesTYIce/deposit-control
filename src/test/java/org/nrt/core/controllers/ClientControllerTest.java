package org.nrt.core.controllers;

import org.junit.jupiter.api.Test;
import org.nrt.core.controller.ClientController;
import org.nrt.core.model.Client;
import org.nrt.core.service.ClientService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    void testCreateClient() throws Exception{
        Client client = new Client();
        client.setClientName("Test Client Name");
        given(clientService.saveClient(any(Client.class))).willReturn(client);
        mockMvc.perform(post("/clients")
                        .contentType("application/json")
                        .content("{\"clientName\":\"Test Client Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientName").value("Test Client Name"));
    }

    @Test
    void testGetOneClient() throws Exception{
        Client client = new Client();
        client.setClientId(1);
        given(clientService.getOneClient(anyInt())).willReturn(Optional.of(client));
        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1));
    }

    @Test
    void testGetAllClients() throws Exception{
        Pageable pageable = PageRequest.of(0, 10);
        given(clientService.getAllClients(any(Pageable.class))).willReturn(new PageImpl<>(Collections.singletonList(new Client())));
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void testUpdateClient() throws Exception{
        Client client = new Client();
        client.setClientId(1);
        client.setClientName("Update Mishka Name");
        given(clientService.updateClient(anyInt(), any(Client.class))).willReturn(client);
        mockMvc.perform(put("/clients/1")
                .contentType("application/json")
                .content("{\"clientName\":\"Update Mishka Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientName").value("Update Mishka Name"));
    }

    @Test
    void testDeleteClient() throws Exception{
        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk());
    }
}
