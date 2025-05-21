package com.projects.Food.Ordering.Website.controller;

import com.projects.Food.Ordering.Website.dto.RestaurantDto;
import com.projects.Food.Ordering.Website.model.Restaurant;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.service.RestaurantServiceImpl;
import com.projects.Food.Ordering.Website.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantServiceImpl restaurantService;
    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(@RequestHeader("Authorization")
                                                             String jwt ){
        List<Restaurant> allRestuarants =  restaurantService.getRestaurantList();
        return new ResponseEntity<>(allRestuarants, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestHeader("Authorization")
                                                              String jwt, String keyword ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Restaurant> searchedRestaurants =  restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(searchedRestaurants, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@RequestHeader("Authorization")
                                                              String jwt, String keyword, @PathVariable Long id ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Restaurant restaurant =  restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @PutMapping("/{restaurantId}/add-to-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(@RequestHeader("Authorization" ) String jwt,
                                                     @PathVariable Long restaurantId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        RestaurantDto restaurant = restaurantService.addToFavorites(restaurantId, user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

}
