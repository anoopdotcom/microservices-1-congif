package com.Cart.CartService.Controller;

import com.Cart.CartService.DTO.CartDTO;
import com.Cart.CartService.Entity.Cart;
import com.Cart.CartService.Service.CartService;
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
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addItem(@Valid @RequestBody CartDTO cartDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        boolean itemAdded=cartService.addItem(cartDTO);

        if(itemAdded){
            return new ResponseEntity<>("Item added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("An error occurred while processing your request. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable int userId){
        List<Cart> cartItems=cartService.getByUserId(userId);
        if(cartItems.isEmpty())return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        boolean deleteItem= cartService.deleteById(id);
        if(!deleteItem){
            return new ResponseEntity<>("Could not process your request, please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Item with ID " + id + " has been deleted successfully.", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItemById(@PathVariable String id, @Valid @RequestBody CartDTO cartDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        boolean updateItem = cartService.updateById(id,cartDTO);

        if(!updateItem) {
            return new ResponseEntity<>("Could not process your request, please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Item updated successfully!", HttpStatus.OK);
    }

}
