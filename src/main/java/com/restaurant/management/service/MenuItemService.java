package com.restaurant.management.service;

import com.restaurant.management.dto.request.CreateMenuItemRequest;
import com.restaurant.management.dto.response.MenuItemResponse;

import java.util.List;

public interface MenuItemService {
    List<MenuItemResponse> findAll();
    MenuItemResponse findById(Long id);
    MenuItemResponse create(CreateMenuItemRequest request);
    MenuItemResponse update(Long id, CreateMenuItemRequest request);
    void delete(Long id);
}
