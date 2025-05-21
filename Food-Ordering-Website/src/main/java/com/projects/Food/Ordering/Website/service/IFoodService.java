package com.projects.Food.Ordering.Website.service;

import com.projects.Food.Ordering.Website.model.Category;
import com.projects.Food.Ordering.Website.model.Food;
import com.projects.Food.Ordering.Website.model.Restaurant;
import com.projects.Food.Ordering.Website.request.CreateFoodRequest;

import java.util.List;

public interface IFoodService {
     Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
     void deleteFood(Long foodId) throws Exception;
     List<Food> getRestaurantsFoodItems(Long restaurantId, boolean veg, boolean nonVeg, String foodCategory);
      List<Food> searchFood(String keyword);
      Food findFoodById(Long foodId) throws Exception;
      Food updateAvailabilityStatus(Long foodId) throws Exception;



}
