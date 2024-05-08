package com.cropdeal.inventoryservice.controller;

import com.cropdeal.inventoryservice.dto.InventoryItemDTO;
import com.cropdeal.inventoryservice.models.InventoryItemEntity;
import com.cropdeal.inventoryservice.service.implementation.InventoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryServiceController {
    @Autowired
    private InventoryServiceImpl inventoryService;
    @PostMapping("/add")
    public ResponseEntity<?> addItem(@Valid @RequestBody InventoryItemDTO inventoryItemDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        boolean saveItem=inventoryService.createInventoryItem(inventoryItemDTO);
        if(saveItem){
            return new ResponseEntity<>("Item added successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("An error occurred while processing your request. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/items")
    public ResponseEntity<?> getAllItems(){
        List<InventoryItemEntity> items=inventoryService.getAllItems();
        if(items.isEmpty())return ResponseEntity.noContent().build();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItemsByOwnerId(@PathVariable Long id){
        List<InventoryItemEntity> items=inventoryService.getItemsByOwnerId(id);
        if(items.isEmpty())return ResponseEntity.noContent().build();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<?> getItemById(@PathVariable String id){
        InventoryItemEntity item=inventoryService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<?> updateItemById(@PathVariable String id, @Valid @RequestBody InventoryItemDTO inventoryItemDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        boolean updateItem = inventoryService.updateItemById(id,inventoryItemDTO);

        if(!updateItem) {
            return new ResponseEntity<>("Could not process your request, please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable String id) {
        boolean deleteItemResult = inventoryService.deleteItemById(id);

        if(!deleteItemResult) {
            return new ResponseEntity<>("Could not process your request, please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Item with ID " + id + " has been deleted successfully.", HttpStatus.OK);
    }
}
