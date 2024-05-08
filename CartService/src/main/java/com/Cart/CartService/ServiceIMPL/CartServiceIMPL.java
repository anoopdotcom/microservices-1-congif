package com.Cart.CartService.ServiceIMPL;

import com.Cart.CartService.DTO.CartDTO;
import com.Cart.CartService.Entity.Cart;
import com.Cart.CartService.Exception.BadRequestException;
import com.Cart.CartService.Exception.ResourceNotFoundException;
import com.Cart.CartService.Repository.CartRepository;
import com.Cart.CartService.Service.CartService;
import com.Cart.CartService.Service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceIMPL implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    InventoryService inventoryService;

    @Override
    public boolean addItem(CartDTO cartDTO) {
        System.out.println(cartDTO);
        if(cartDTO.getQuantity()<=0 || cartDTO.getUserId()<=0 || cartDTO.getItemId()==null || cartDTO.getItemId().isEmpty()){
            throw new BadRequestException("User ID, Item ID, Quantity, cannot be empty, Quantity should be >0.");
        }
        Cart entity=new Cart();
        entity.setItem(inventoryService.getItemById(cartDTO.getItemId()));
        BeanUtils.copyProperties(cartDTO,entity);
        try{
            Optional<Cart> savedCartEntity= Optional.of(cartRepository.save(entity));
            return true;
        } catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public List<Cart> getByUserId(int userId) {
        List<Cart> items=cartRepository.findAllByUserId(userId);
        if(items.isEmpty()){
            throw new ResourceNotFoundException("No items in cart!");
        }
        return items;
    }

    @Override
    public boolean deleteById(String id) {
        Optional<Cart> fetchedItem=cartRepository.findById(id);
        if(fetchedItem.isEmpty()){
            throw new ResourceNotFoundException("No items with given id in cart!");
        }
        cartRepository.deleteById(id);
        return true;    }

    @Override
    public boolean updateById(String id, CartDTO cartDTO) {
        Optional<Cart> fetchedItem=cartRepository.findById(id);
        if(fetchedItem.isEmpty())throw new ResourceNotFoundException("Cannot find item with given id!");
        Cart entity=fetchedItem.get();
        entity.setQuantity(cartDTO.getQuantity());
        try{
            Optional<Cart> updatedItem=Optional.of(cartRepository.save(entity));
            return true;
        } catch(NullPointerException e){
            return false;
        }    }

    @Override
    public double calculateTotalAmount(List<Cart> list) {
        double amount=0;
        for(Cart i:list){
            amount+=i.getItem().getPricePerUnit()*i.getQuantity();
        }
        return amount;
    }
    }

