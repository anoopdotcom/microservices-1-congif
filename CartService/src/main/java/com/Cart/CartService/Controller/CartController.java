package com.Cart.CartService.Controller;

import com.Cart.CartService.Entity.Cart;
import com.Cart.CartService.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping
    public Cart createCart(@RequestBody Cart cart){
        return cartService.saveCart(cart);
    }

    @GetMapping("/item/{cartId}")
    public Cart getCartById(@PathVariable String cartId){
        return cartService.getCartById(cartId);
    }

    @DeleteMapping("/delete/{cartId}")
    public boolean deleteCart(@PathVariable String cartId){
        cartService.deleteCartById(cartId);
        return true;
    }

    @PutMapping("/updateCart/{cartId}")
    public boolean updateCart(String cartId,Cart cart){
        boolean updateCartResult = cartService.updateCartById(cartId,cart);
        if(!updateCartResult){
            return false;
        }
        else return true;
    }

    @GetMapping("/price")
    public double getCartPrice(Cart cart){
        double price = cartService.getTotalPrice(cart);
        return price;
    }
}
