package com.restaurant.management.controller;

import com.restaurant.management.dto.request.CreateMenuItemRequest;
import com.restaurant.management.dto.response.MenuItemResponse;
import com.restaurant.management.service.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu-items")
public class MenuItemControllerV1 {

    private final MenuItemService service;

    public MenuItemControllerV1(MenuItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<MenuItemResponse> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getById(@PathVariable Long id) {
        MenuItemResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MenuItemResponse> create(@RequestBody CreateMenuItemRequest request) {
        MenuItemResponse response = service.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponse> update(@PathVariable Long id,
                                                   @RequestBody CreateMenuItemRequest request) {
        MenuItemResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

