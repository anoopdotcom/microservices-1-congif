package com.cropdeal.inventoryservice;

import com.cropdeal.inventoryservice.dto.InventoryItemDTO;
import com.cropdeal.inventoryservice.models.InventoryItemEntity;
import com.cropdeal.inventoryservice.repository.InventoryItemRepository;
import com.cropdeal.inventoryservice.service.implementation.InventoryServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {InventoryServiceMockitoTests.class})
public class InventoryServiceMockitoTests {
    @Mock
    InventoryItemRepository inventoryItemRepository;
    @InjectMocks
    InventoryServiceImpl inventoryService;

    @Test @Order(1)
    public void test_createInventoryItem(){
        InventoryItemDTO inventoryItemDTO=new InventoryItemDTO();
        inventoryItemDTO.setName("Kesar");
        inventoryItemDTO.setCategory("Ornamental");
        //inventoryItemDTO.setDescription("");
        inventoryItemDTO.setQuantity(400);
        inventoryItemDTO.setPricePerUnit(500.0);
        inventoryItemDTO.setOwnerId(1);
        InventoryItemEntity entity= new InventoryItemEntity();
        BeanUtils.copyProperties(inventoryItemDTO,entity);

        when(inventoryItemRepository.save(any(InventoryItemEntity.class))).thenReturn(entity);

        assertTrue(inventoryService.createInventoryItem(inventoryItemDTO));
    }

    @Test @Order(2)
    public void test_createInventoryItem_SaveItemFailed() {
        InventoryItemDTO inventoryItemDTO=new InventoryItemDTO();
        inventoryItemDTO.setName("Kesar");
        inventoryItemDTO.setCategory("Ornamental");
        //inventoryItemDTO.setDescription("");
        inventoryItemDTO.setQuantity(400);
        inventoryItemDTO.setPricePerUnit(500.0);
        inventoryItemDTO.setOwnerId(1);
        InventoryItemEntity entity= new InventoryItemEntity();
        BeanUtils.copyProperties(inventoryItemDTO,entity);

        //when(userRepository.save(any(UserEntity.class))).thenThrow(NullPointerException.class);

        when(inventoryItemRepository.save(any(InventoryItemEntity.class))).thenThrow(NullPointerException.class);

        assertFalse(inventoryService.createInventoryItem(inventoryItemDTO));

    }


}
