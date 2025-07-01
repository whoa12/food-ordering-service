package com.projects.Food.Ordering.Website.controller;

import com.projects.Food.Ordering.Website.model.Order;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.service.OrderServiceImpl;
import com.projects.Food.Ordering.Website.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminOrderController {
    private final UserServiceImpl userService;
    private final OrderServiceImpl orderService;

    @GetMapping("order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getOrderHistoryOfARestaurant(@PathVariable Long restaurantId,
                                                                    @PathVariable(required = false) String order_status,@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        List<Order> orders = orderService.getRestaurantsOrder(restaurantId, order_status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId,
                                                         @PathVariable String orderStatus, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Order updatedOrderStatus = orderService.updateOrder(orderId, orderStatus);
        return new ResponseEntity<>(updatedOrderStatus, HttpStatus.OK);

    }
}
