package com.Cart.CartService.Service;

import com.Cart.CartService.DTO.CartDTO;
import com.Cart.CartService.Entity.Cart;

import java.util.List;

public interface CartService {
    boolean addItem(CartDTO cartDTO);
    List<Cart> getByUserId(int userId);
    boolean deleteById(String id);
    boolean updateById(String id,CartDTO cartDTO);
    double calculateTotalAmount(List<Cart> list);
}
