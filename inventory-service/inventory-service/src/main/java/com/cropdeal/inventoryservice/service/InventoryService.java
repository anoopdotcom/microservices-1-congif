package com.cropdeal.inventoryservice.service;

import com.cropdeal.inventoryservice.dto.InventoryItemDTO;
import com.cropdeal.inventoryservice.models.InventoryItemEntity;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    boolean createInventoryItem(InventoryItemDTO inventoryItemDTO);
    List<InventoryItemEntity> getAllItems();
    List<InventoryItemEntity> getItemsByOwnerId(Long ownerId);
    InventoryItemEntity getItemById(String id);
    boolean updateItemById(String id,InventoryItemDTO inventoryItemDTO);
    boolean deleteItemById(String id);

}
