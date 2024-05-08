package com.Cart.CartService.DTO;

import lombok.Data;

@Data
public class CartDTO {
    private int userId;
    private String itemId;
    private int quantity;
}
