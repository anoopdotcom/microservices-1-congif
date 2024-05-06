package com.Cart.CartService.Service;

import com.Cart.CartService.Entity.Cart;

public interface CartService {
    Cart saveCart(Cart cart);
    Cart getCartById(String cartId);

    boolean deleteCartById(String cartId);

    boolean updateCartById(String cartId,Cart cart);

    public double getTotalPrice(Cart cart);
}
