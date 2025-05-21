package com.projects.Food.Ordering.Website.controller;

import com.projects.Food.Ordering.Website.model.Food;
import com.projects.Food.Ordering.Website.model.Restaurant;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.request.CreateFoodRequest;
import com.projects.Food.Ordering.Website.response.MessageResponse;
import com.projects.Food.Ordering.Website.service.FoodServiceImpl;
import com.projects.Food.Ordering.Website.service.RestaurantServiceImpl;
import com.projects.Food.Ordering.Website.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/food")
@RequiredArgsConstructor
public class AdminFoodController {
    private final FoodServiceImpl foodService;
    private final UserServiceImpl userService;
    private final RestaurantServiceImpl restaurantService;

    @PostMapping
    public ResponseEntity<Food>  createFood(@RequestHeader("Authorization") String jwt,
                                            @RequestBody CreateFoodRequest req) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Restaurant restaurant = restaurantService.findRestaurantByUserId(req.getRestaurantId());
        Food food = foodService.createFood(req, req.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        foodService.deleteFood(id);
        MessageResponse msg = new MessageResponse();
        msg.setMessage("Deletion successful!");
        return new ResponseEntity<>(msg, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateAvailabilityStatus(@PathVariable Long id,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        User user= userService.findUserByJwt(jwt);
        Food food= foodService.updateAvailabilityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
