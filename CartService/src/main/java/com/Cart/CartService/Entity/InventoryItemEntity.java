package com.Cart.CartService.Entity;

import lombok.*;

@Data
public class InventoryItemEntity {

    private String id;
    private String name;
    private String category;
    private String description;
    private int quantity;
    private double pricePerUnit;
    private long ownerId;
}
