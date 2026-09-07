package com.restaurant.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.management.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {}
