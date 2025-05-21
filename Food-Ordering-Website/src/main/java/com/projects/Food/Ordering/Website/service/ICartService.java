package com.projects.Food.Ordering.Website.service;

import com.projects.Food.Ordering.Website.model.Cart;
import com.projects.Food.Ordering.Website.model.CartItems;
import com.projects.Food.Ordering.Website.request.AddCartItemRequest;

public interface ICartService {
     CartItems addToCart(AddCartItemRequest req, String jwt) throws Exception;
     CartItems updateCartItems(Long cartItemId, int quantity) throws Exception;
     Cart removeItem(Long cartItemId, String jwt) throws Exception;
     double calculateTotal(Cart cart) throws Exception;
     Cart findCartById(Long id) throws Exception;
     Cart findCartByUserId(Long userId) throws Exception;
     Cart clearCart(String jwt) throws Exception;

}
