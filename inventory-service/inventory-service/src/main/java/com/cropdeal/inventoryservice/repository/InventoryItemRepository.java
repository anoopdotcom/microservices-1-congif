package com.cropdeal.inventoryservice.repository;

import com.cropdeal.inventoryservice.models.InventoryItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository extends MongoRepository<InventoryItemEntity, String> {
    List<InventoryItemEntity> findByName(String name);
    List<InventoryItemEntity> findByCategory(String category);
    List<InventoryItemEntity> findByOwnerId(Long ownerId);

}
