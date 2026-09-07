package com.restaurant.management.controller;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
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

