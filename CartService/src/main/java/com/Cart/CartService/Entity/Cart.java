package com.Cart.CartService.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cart {
    @Id
    private String cartId;
    private String userId;
    private List<String> inventoryItemId;
    //private int quantity;
//    private List<InventoryItemEntity> inventoryItemEntityList;

}
