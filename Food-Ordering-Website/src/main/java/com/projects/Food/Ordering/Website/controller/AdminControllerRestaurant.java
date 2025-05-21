package com.projects.Food.Ordering.Website.controller;

import com.projects.Food.Ordering.Website.model.Restaurant;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.request.CreateRestaurantRequest;
import com.projects.Food.Ordering.Website.response.MessageResponse;
import com.projects.Food.Ordering.Website.service.RestaurantServiceImpl;
import com.projects.Food.Ordering.Website.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
@RequiredArgsConstructor
public class AdminControllerRestaurant {
    private final RestaurantServiceImpl restaurantService;
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(req, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @PathVariable Long restaurantId, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
                                                       @PathVariable Long restaurantId,@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        restaurantService.deleteRestaurant(restaurantId);
        MessageResponse res = new MessageResponse();
        res.setMessage("Restaurant deleted successfully!");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/status/{restaurantId}")
    public ResponseEntity<Restaurant> updateStatusOfRestaurant(@RequestBody CreateRestaurantRequest req,
                                                            @PathVariable Long restaurantId,@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> getRestaurantByUserId(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        Restaurant restaurant = restaurantService.findRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
