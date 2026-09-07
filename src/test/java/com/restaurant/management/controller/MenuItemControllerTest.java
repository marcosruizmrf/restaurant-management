package com.restaurant.management.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.management.dto.request.CreateMenuItemRequest;
import com.restaurant.management.dto.response.MenuItemResponse;
import com.restaurant.management.service.MenuItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MenuItemControllerV1.class)
class MenuItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuItemService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateMenuItem() throws Exception {
        CreateMenuItemRequest request = new CreateMenuItemRequest(
                "Pizza", "Deliciosa pizza", 50.0, true, "/img/pizza.png", 1L);

        MenuItemResponse response = new MenuItemResponse(
                10L, "Pizza", "Deliciosa pizza", 50.0, true, "/img/pizza.png", 1L);

        Mockito.when(service.create(any(CreateMenuItemRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/menu-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.nome").value("Pizza"));
    }

    @Test
    void testGetAllMenuItems() throws Exception {
        MenuItemResponse i1 = new MenuItemResponse(1L, "Pizza", "Queijo", 40.0, true, "/img/pizza.png", 1L);
        MenuItemResponse i2 = new MenuItemResponse(2L, "Hamburguer", "Carne", 25.0, false, "/img/burger.png", 1L);

        Mockito.when(service.findAll()).thenReturn(List.of(i1, i2));

        mockMvc.perform(get("/api/v1/menu-items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Pizza"))
                .andExpect(jsonPath("$[1].nome").value("Hamburguer"));
    }

    @Test
    void testGetMenuItemById() throws Exception {
        MenuItemResponse response = new MenuItemResponse(
                5L, "Sushi", "Peixe cru", 60.0, true, "/img/sushi.png", 2L);

        Mockito.when(service.findById(5L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/menu-items/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.nome").value("Sushi"));
    }

    @Test
    void testUpdateMenuItem() throws Exception {
        CreateMenuItemRequest request = new CreateMenuItemRequest(
                "Pizza Atualizada", "Com borda recheada", 55.0, true, "/img/pizza2.png", 1L);

        MenuItemResponse response = new MenuItemResponse(
                15L, "Pizza Atualizada", "Com borda recheada", 55.0, true, "/img/pizza2.png", 1L);

        Mockito.when(service.update(eq(15L), any(CreateMenuItemRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/v1/menu-items/15")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Pizza Atualizada"))
                .andExpect(jsonPath("$.descricao").value("Com borda recheada"));
    }

    @Test
    void testDeleteMenuItem() throws Exception {
        mockMvc.perform(delete("/api/v1/menu-items/20"))
                .andExpect(status().isNoContent());
    }
}