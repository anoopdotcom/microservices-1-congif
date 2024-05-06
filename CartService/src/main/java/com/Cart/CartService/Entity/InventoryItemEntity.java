package com.Cart.CartService.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemEntity {

    private String id;
    private String name;
    private String category;
    private String description;
    private int quantity;
    private double pricePerUnit;
    private long ownerId;
}
