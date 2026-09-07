package com.restaurant.management.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.restaurant.management.service.RestaurantService;
import com.restaurant.management.dto.request.CreateRestaurantRequest;
import com.restaurant.management.dto.response.RestaurantResponse;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantControllerV1 {
    private final RestaurantService service;

    public RestaurantControllerV1(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public RestaurantResponse create(@RequestBody CreateRestaurantRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<RestaurantResponse> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RestaurantResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public RestaurantResponse update(@PathVariable Long id, @RequestBody CreateRestaurantRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

