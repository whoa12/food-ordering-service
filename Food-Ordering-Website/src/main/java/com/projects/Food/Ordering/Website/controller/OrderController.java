package com.projects.Food.Ordering.Website.controller;

import com.projects.Food.Ordering.Website.model.Order;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.request.OrderRequest;
import com.projects.Food.Ordering.Website.service.OrderServiceImpl;
import com.projects.Food.Ordering.Website.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
   private final OrderServiceImpl orderService;

   private final UserServiceImpl userService;

   @PostMapping("/order")
   public ResponseEntity<Order> createOrder(@RequestHeader("Authorization") String jwt, @RequestBody OrderRequest req) throws Exception {
      User user = userService.findUserByJwt(jwt);
      Order order = orderService.createOrder(req, user);
      return new ResponseEntity<>(order, HttpStatus.CREATED);
   }

   @GetMapping("/order/user")
   public ResponseEntity<List<Order>> getOrderHistory(
           @RequestHeader("Authorization") String jwt
   ) throws Exception {
      User user = userService.findUserByJwt(jwt);
      List<Order> order = orderService.getUsersOrder(user.getId());
      return new ResponseEntity<>(order, HttpStatus.OK);
   }
}
