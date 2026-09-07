package com.restaurant.management.service;

public interface RestaurantService {
    List<RestaurantResponse> findAll();
    RestaurantResponse findById(Long id);
    RestaurantResponse create(CreateRestaurantRequest request);
    RestaurantResponse update(Long id, CreateRestaurantRequest request);
    void delete(Long id);
}
