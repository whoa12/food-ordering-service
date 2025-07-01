package com.projects.Food.Ordering.Website.repository;

import com.projects.Food.Ordering.Website.model.CategoryEntity;
import com.projects.Food.Ordering.Website.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    //to display list of food using restaurantId
    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.description LIKE %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);


    List<Food> findByFoodCategoryEntity(CategoryEntity categoryEntity);
}
