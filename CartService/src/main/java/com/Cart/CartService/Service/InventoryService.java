package com.Cart.CartService.Service;

import com.Cart.CartService.Entity.InventoryItemEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
@Service
public interface InventoryService {
    @GetMapping("/inventory/item/{id}")
    public InventoryItemEntity getItemById(@PathVariable String id);
}
