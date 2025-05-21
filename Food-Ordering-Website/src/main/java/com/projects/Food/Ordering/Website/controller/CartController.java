package com.projects.Food.Ordering.Website.controller;

import com.projects.Food.Ordering.Website.model.Cart;
import com.projects.Food.Ordering.Website.model.CartItems;
import com.projects.Food.Ordering.Website.request.AddCartItemRequest;
import com.projects.Food.Ordering.Website.request.UpdateCartItem;
import com.projects.Food.Ordering.Website.service.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CartController {

    private CartServiceImpl cartService;

    @GetMapping("/cart/add")
    public ResponseEntity<CartItems> addCartItemToCart(@RequestBody AddCartItemRequest req,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        CartItems cartItem = cartService.addToCart(req, jwt);

        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItems> updateCartItem(@RequestBody UpdateCartItem req,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        CartItems cartItem = cartService.updateCartItems(req.getCartItemId(), req.getQuantity());

        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> deleteCartItem(@PathVariable Long id,
                                                    @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.removeItem(id, jwt);

        return new ResponseEntity<>(cart, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(
                                                    @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.clearCart(jwt);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


}
