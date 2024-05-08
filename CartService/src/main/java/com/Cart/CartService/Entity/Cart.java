package com.Cart.CartService.Entity;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
@Data
public class Cart {
    @Id
    private String cartId;
    private int userId;
    private String itemId;
    private int quantity;
    private InventoryItemEntity item;

}
