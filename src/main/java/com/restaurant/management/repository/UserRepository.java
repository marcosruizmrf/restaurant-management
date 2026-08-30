package com.restaurant.management.repository;

import com.restaurant.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    List<User> findByNameContainingIgnoreCase(String name);

    Optional<User> findByLogin(String login);
}
