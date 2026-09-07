package com.restaurant.management.service.impl;

import org.springframework.stereotype.Service;
import com.restaurant.management.service.RestaurantService;
import com.restaurant.management.repository.RestaurantRepository;
import com.restaurant.management.repository.UserRepository;
import com.restaurant.management.dto.request.CreateRestaurantRequest;
import com.restaurant.management.dto.response.RestaurantResponse;
import com.restaurant.management.model.Restaurant;
import com.restaurant.management.model.User;
import com.restaurant.management.exception.UserNotFoundException;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;
    private final UserRepository userRepository;

    public RestaurantServiceImpl(RestaurantRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public RestaurantResponse create(CreateRestaurantRequest request) {
        User owner = userRepository.findById(request.ownerId())
                .orElseThrow(() -> new UserNotFoundException("Owner not found"));

        Restaurant restaurant = new Restaurant();
        restaurant.setNome(request.nome());
        restaurant.setEndereco(request.endereco());
        restaurant.setTipoCozinha(request.tipoCozinha());
        restaurant.setHorarioFuncionamento(request.horarioFuncionamento());
        restaurant.setOwner(owner);

        Restaurant saved = repository.save(restaurant);
        return new RestaurantResponse(saved.getId(), saved.getNome(), saved.getEndereco(),
                saved.getTipoCozinha(), saved.getHorarioFuncionamento(), saved.getOwner().getId());
    }

    // outros métodos (findAll, findById, update, delete)
}
