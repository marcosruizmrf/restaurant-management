package com.restaurant.management.service.impl;

package management.service.impl;

import management.dto.request.CreateMenuItemRequest;
import management.model.MenuItem;
import management.model.Restaurant;
import management.repository.MenuItemRepository;
import management.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class MenuItemServiceImplTest {

    private final MenuItemRepository menuItemRepository = Mockito.mock(MenuItemRepository.class);
    private final RestaurantRepository restaurantRepository = Mockito.mock(RestaurantRepository.class);
    private final MenuItemServiceImpl service = new MenuItemServiceImpl(menuItemRepository, restaurantRepository);

    @Test
    void testCreateMenuItem() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        Mockito.when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        Mockito.when(menuItemRepository.save(any(MenuItem.class))).thenAnswer(invocation -> {
            MenuItem item = invocation.getArgument(0);
            item.setId(10L);
            return item;
        });

        CreateMenuItemRequest request = new CreateMenuItemRequest(
                "Pizza", "Deliciosa pizza", 50.0, true, "/img/pizza.png", 1L);

        var response = service.create(request);

        assertEquals("Pizza", response.nome());
        assertEquals(10L, response.id());
        assertEquals(1L, response.restaurantId());
    }
}
