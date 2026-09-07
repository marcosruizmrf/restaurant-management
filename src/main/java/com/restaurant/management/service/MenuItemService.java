package com.restaurant.management.service;

package management.service;

import management.dto.request.CreateMenuItemRequest;
import management.dto.response.MenuItemResponse;

import java.util.List;

public interface MenuItemService {
    List<MenuItemResponse> findAll();
    MenuItemResponse findById(Long id);
    MenuItemResponse create(CreateMenuItemRequest request);
    MenuItemResponse update(Long id, CreateMenuItemRequest request);
    void delete(Long id);
}
