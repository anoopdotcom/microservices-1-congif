package com.cropdeal.inventoryservice.models;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class InventoryItemEntity {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String category;
    private String description;
    @NotNull
    private int quantity;
    @NotNull
    private double pricePerUnit;

    private long ownerId;
}
