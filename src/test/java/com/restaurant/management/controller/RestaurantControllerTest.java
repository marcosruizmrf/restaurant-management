package com.restaurant.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.management.dto.request.CreateRestaurantRequest;
import com.restaurant.management.dto.response.RestaurantResponse;
import com.restaurant.management.service.RestaurantService;
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

@WebMvcTest(RestaurantControllerV1.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateRestaurant() throws Exception {
        CreateRestaurantRequest request = new CreateRestaurantRequest(
                "Restaurante Central", "Rua A, 123", "Italiana", "10h-22h", 5L);

        RestaurantResponse response = new RestaurantResponse(
                100L, "Restaurante Central", "Rua A, 123", "Italiana", "10h-22h", 5L);

        Mockito.when(service.create(any(CreateRestaurantRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.nome").value("Restaurante Central"));
    }

    @Test
    void testGetAllRestaurants() throws Exception {
        RestaurantResponse r1 = new RestaurantResponse(1L, "Restaurante A", "Rua 1", "Japonesa", "9h-21h", 2L);
        RestaurantResponse r2 = new RestaurantResponse(2L, "Restaurante B", "Rua 2", "Italiana", "10h-22h", 3L);

        Mockito.when(service.findAll()).thenReturn(List.of(r1, r2));

        mockMvc.perform(get("/api/v1/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Restaurante A"))
                .andExpect(jsonPath("$[1].nome").value("Restaurante B"));
    }

    @Test
    void testGetRestaurantById() throws Exception {
        RestaurantResponse response = new RestaurantResponse(
                10L, "Restaurante X", "Rua X", "Brasileira", "11h-23h", 4L);

        Mockito.when(service.findById(10L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/restaurants/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.tipoCozinha").value("Brasileira"));
    }

    @Test
    void testUpdateRestaurant() throws Exception {
        CreateRestaurantRequest request = new CreateRestaurantRequest(
                "Restaurante Atualizado", "Rua Nova, 456", "Mexicana", "12h-00h", 6L);

        RestaurantResponse response = new RestaurantResponse(
                20L, "Restaurante Atualizado", "Rua Nova, 456", "Mexicana", "12h-00h", 6L);

        Mockito.when(service.update(eq(20L), any(CreateRestaurantRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/v1/restaurants/20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Restaurante Atualizado"))
                .andExpect(jsonPath("$.tipoCozinha").value("Mexicana"));
    }

    @Test
    void testDeleteRestaurant() throws Exception {
        mockMvc.perform(delete("/api/v1/restaurants/30"))
                .andExpect(status().isNoContent());
    }
}
