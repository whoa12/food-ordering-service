package com.projects.Food.Ordering.Website.repository;

import com.projects.Food.Ordering.Website.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
   @Query("SELECT r FROM Restaurant r WHERE r.name LIKE %:query% OR r.description LIKE %:query% OR r.cuisineType LIKE %:query%")
   List<Restaurant>  findBySearchQuery(String query);
   Restaurant findByOwnerId(Long id);

}
