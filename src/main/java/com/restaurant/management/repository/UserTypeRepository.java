package com.restaurant.management.repository;

import com.restaurant.management.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    Optional<UserType> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}
