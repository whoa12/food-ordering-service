package com.projects.Food.Ordering.Website.service;

import com.projects.Food.Ordering.Website.model.Order;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.request.OrderRequest;

import java.util.List;

public interface IOrderService {
    Order createOrder(OrderRequest order, User user) throws Exception;
    Order updateOrder(Long orderId, String orderStauts) throws Exception;
    void cancelOrder(Long orderId) throws Exception;


    List<Order> getUsersOrder(Long userId) throws Exception;

    Order findOrderById(Long orderId) throws Exception;

    List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;
}
