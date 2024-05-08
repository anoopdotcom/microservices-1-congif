package com.cropdeal.inventoryservice.dto;

import lombok.Data;

@Data
public class InventoryItemDTO {
    private String name;
    private String category;
    private String description;
    private int quantity;
    private double pricePerUnit;
    private long ownerId;
}
