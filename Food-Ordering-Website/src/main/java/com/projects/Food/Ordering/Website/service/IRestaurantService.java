package com.projects.Food.Ordering.Website.service;

import com.projects.Food.Ordering.Website.dto.RestaurantDto;
import com.projects.Food.Ordering.Website.model.Restaurant;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.request.CreateRestaurantRequest;

import java.util.List;

public interface IRestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;
    public void deleteRestaurant(Long restaurantId) throws Exception;
    public List<Restaurant> getRestaurantList();
    public List<Restaurant> searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Long restaurantId) throws Exception;
    public Restaurant findRestaurantByUserId(Long userId) throws Exception;
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;
    public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
