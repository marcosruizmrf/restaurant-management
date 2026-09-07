package com.restaurant.management.service;

import java.util.List;
import com.restaurant.management.dto.response.RestaurantResponse;
import com.restaurant.management.dto.request.CreateRestaurantRequest;

public interface RestaurantService {
    List<RestaurantResponse> findAll();
    RestaurantResponse findById(Long id);
    RestaurantResponse create(CreateRestaurantRequest request);
    RestaurantResponse update(Long id, CreateRestaurantRequest request);
    void delete(Long id);
}
