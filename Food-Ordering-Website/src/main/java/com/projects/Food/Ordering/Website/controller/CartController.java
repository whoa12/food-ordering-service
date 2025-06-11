package com.projects.Food.Ordering.Website.controller;

import com.projects.Food.Ordering.Website.model.Cart;
import com.projects.Food.Ordering.Website.model.CartItems;
import com.projects.Food.Ordering.Website.model.User;
import com.projects.Food.Ordering.Website.request.AddCartItemRequest;
import com.projects.Food.Ordering.Website.request.UpdateCartItem;
import com.projects.Food.Ordering.Website.service.CartServiceImpl;
import com.projects.Food.Ordering.Website.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartService;
    private final UserServiceImpl userService;

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
        User user = userService.findUserByJwt(jwt);

        Cart cart = cartService.clearCart(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


}
