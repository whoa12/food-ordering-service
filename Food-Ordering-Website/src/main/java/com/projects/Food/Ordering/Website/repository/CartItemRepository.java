package com.projects.Food.Ordering.Website.repository;

import com.projects.Food.Ordering.Website.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {
}
