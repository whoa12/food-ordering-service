package com.projects.Food.Ordering.Website.controller;

import com.projects.Food.Ordering.Website.model.Food;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.service.FoodServiceImpl;
import com.projects.Food.Ordering.Website.service.RestaurantServiceImpl;
import com.projects.Food.Ordering.Website.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
    private final UserServiceImpl userService;
    private final FoodServiceImpl foodService;
    private RestaurantServiceImpl restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>>searchFood(@RequestParam String name,
                                                @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        List<Food> foodList = foodService.searchFood(name);
        return  new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>>findRestaurantFood(@RequestParam(required = false) boolean veg,
                                                       @RequestParam(required = false)  boolean nonveg, @PathVariable Long restaurantId, @RequestParam(required = false) String foodCategory
                                                        ,@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        List<Food> foodList = foodService.getRestaurantsFoodItems(restaurantId, veg, nonveg, foodCategory);
        return  new ResponseEntity<>(foodList, HttpStatus.OK);
    }


}
