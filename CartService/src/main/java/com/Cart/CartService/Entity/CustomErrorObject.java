package com.Cart.CartService.Entity;

import lombok.Data;

@Data
public class CustomErrorObject {
    private Integer statusCode;
    private String message;
}
