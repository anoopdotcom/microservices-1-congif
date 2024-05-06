package com.Cart.CartService.ServiceIMPL;

import com.Cart.CartService.Entity.Cart;
import com.Cart.CartService.Entity.InventoryItemEntity;
import com.Cart.CartService.Repository.CartRepository;
import com.Cart.CartService.Service.CartService;
import com.Cart.CartService.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class CartServiceIMPL implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    InventoryService inventoryService;
    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(String cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }

    @Override
    public boolean deleteCartById(String cartId) {
        if(cartRepository.findById(cartId).isPresent()){
            cartRepository.deleteById(cartId);
            return true;
        }
        else return false;
    }

    @Override
    public boolean updateCartById(String cartId,Cart cart) {
        Cart existingCart = getCartById(cartId);
        if(cartRepository.findById(cartId).isPresent()){
            existingCart.setUserId(cart.getUserId() != null ? cart.getUserId() : existingCart.getUserId());
            return true;
        }
        else return false;
    }

    public double getTotalPrice(Cart cart){
        double sum = 0;
        for(String s: cart.getInventoryItemId()){       //1,2,5,3
            InventoryItemEntity i = inventoryService.getItemById(s);
            int qua = i.getQuantity();
            double price = i.getPricePerUnit();
            sum = price*qua + sum;
        }

        return sum;
    }
}
