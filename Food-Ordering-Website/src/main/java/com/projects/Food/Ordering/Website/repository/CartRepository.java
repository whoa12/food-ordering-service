package com.projects.Food.Ordering.Website.repository;

import com.projects.Food.Ordering.Website.model.Cart;
import com.projects.Food.Ordering.Website.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByCustomerId(Long userId);

}
