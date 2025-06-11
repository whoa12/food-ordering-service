package com.projects.Food.Ordering.Website.service;

import com.projects.Food.Ordering.Website.model.Cart;
import com.projects.Food.Ordering.Website.model.CartItems;
import com.projects.Food.Ordering.Website.model.Food;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.repository.CartItemRepository;
import com.projects.Food.Ordering.Website.repository.CartRepository;
import com.projects.Food.Ordering.Website.request.AddCartItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService{
    private final UserServiceImpl userService;
    private final FoodServiceImpl foodService;
    private final RestaurantServiceImpl restaurantService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItems addToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());
        for(CartItems cartItem: cart.getCartItemsList()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItems(cartItem.getId(), newQuantity);

            }
        }
        CartItems newCartItem = new CartItems();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setTotalPrice(req.getQuantity()* food.getPrice());
        CartItems savedCartItem = cartItemRepository.save(newCartItem);
        cart.getCartItemsList().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItems updateCartItems(Long cartItemId, int quantity) throws Exception {
        Optional<CartItems> cartItemsOptional = cartItemRepository.findById(cartItemId);
        if(cartItemsOptional.isEmpty()){
            throw new Exception("Not found!");
        }
        CartItems item = cartItemsOptional.get();
        item.setQuantity(item.getQuantity());
        item.setTotalPrice(item.getFood().getPrice()*quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItem(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItems> cartItemsOptional = cartItemRepository.findById(cartItemId);
        if (cartItemsOptional.isEmpty()) {
            throw new Exception("Not found!");
        }
        CartItems item = cartItemsOptional.get();
        cart.getCartItemsList().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public double calculateTotal(Cart cart) throws Exception {
        double total = 0;
        for(CartItems cartItem: cart.getCartItemsList()){
            total+= cartItem.getFood().getPrice()* cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isEmpty()){
            throw  new Exception("Cart not found!");
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
       // User user = userService.findUserByJwt(jwt);

        return cartRepository.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        //User user = userService.findUserByJwt(jwt);
        Cart cart = findCartByUserId(userId);
        cart.getCartItemsList().clear();
        return cartRepository.save(cart);
    }
}
