package com.restaurant.management.service.impl;

package management.service.impl;

import management.dto.request.CreateMenuItemRequest;
import management.dto.response.MenuItemResponse;
import management.exception.UserNotFoundException;
import management.model.MenuItem;
import management.model.Restaurant;
import management.repository.MenuItemRepository;
import management.repository.RestaurantRepository;
import management.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository,
                               RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<MenuItemResponse> findAll() {
        return menuItemRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemResponse findById(Long id) {
        return menuItemRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }

    @Override
    public MenuItemResponse create(CreateMenuItemRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuItem menuItem = new MenuItem();
        menuItem.setNome(request.nome());
        menuItem.setDescricao(request.descricao());
        menuItem.setPreco(request.preco());
        menuItem.setDisponivelNoLocal(request.disponivelNoLocal());
        menuItem.setFotoPath(request.fotoPath());
        menuItem.setRestaurant(restaurant);

        MenuItem saved = menuItemRepository.save(menuItem);
        return toResponse(saved);
    }

    @Override
    public MenuItemResponse update(Long id, CreateMenuItemRequest request) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        Restaurant restaurant = restaurantRepository.findById(request.restaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        menuItem.setNome(request.nome());
        menuItem.setDescricao(request.descricao());
        menuItem.setPreco(request.preco());
        menuItem.setDisponivelNoLocal(request.disponivelNoLocal());
        menuItem.setFotoPath(request.fotoPath());
        menuItem.setRestaurant(restaurant);

        MenuItem updated = menuItemRepository.save(menuItem);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        menuItemRepository.deleteById(id);
    }

    private MenuItemResponse toResponse(MenuItem menuItem) {
        return new MenuItemResponse(
                menuItem.getId(),
                menuItem.getNome(),
                menuItem.getDescricao(),
                menuItem.getPreco(),
                menuItem.getDisponivelNoLocal(),
                menuItem.getFotoPath(),
                menuItem.getRestaurant().getId()
        );
    }
}