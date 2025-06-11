package com.projects.Food.Ordering.Website.repository;

import com.projects.Food.Ordering.Website.model.OrderedItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderedItems, Long> {

}
