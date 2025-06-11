package com.projects.Food.Ordering.Website.service;

import com.projects.Food.Ordering.Website.model.*;
import com.projects.Food.Ordering.Website.repository.AddressRepository;
import com.projects.Food.Ordering.Website.repository.OrderItemRepository;
import com.projects.Food.Ordering.Website.repository.OrderRepository;
import com.projects.Food.Ordering.Website.repository.UserRepository;
import com.projects.Food.Ordering.Website.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService{

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final RestaurantServiceImpl restaurantService;
    private final CartServiceImpl cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippedAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippedAddress);
        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setRestaurant(restaurant);
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderedItems> orderedItemsList = new ArrayList<>();
        for(CartItems cartItem : cart.getCartItemsList()){
            OrderedItems orderedItem = new OrderedItems();
            orderedItem.setFood(cartItem.getFood());
            orderedItem.setQuantity(cartItem.getQuantity());
            orderedItem.setTotalPrice(cartItem.getTotalPrice());

            OrderedItems savedOrderedItem = orderItemRepository.save(orderedItem);
            orderedItemsList.add(savedOrderedItem);

        }
        double totalPrice = cartService.calculateTotal(cart);
        createdOrder.setOrderedItemsList(orderedItemsList);
        createdOrder.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(createdOrder);

        restaurant.getOrders().add(savedOrder);
        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY")|| orderStatus.equals("DELIVERED")
        || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please select a valid order status!");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.delete(order);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception{
        return orderRepository.findByCustomerId(userId);
    }



    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("Order not found!");
        }
        return optionalOrder.get();
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);

        if (orderStatus != null) {
            orders = orders.stream()
                    .filter(order -> order.getOrderStatus().equalsIgnoreCase(orderStatus))
                    .collect(Collectors.toList());
        }

        return orders;
    }

}
