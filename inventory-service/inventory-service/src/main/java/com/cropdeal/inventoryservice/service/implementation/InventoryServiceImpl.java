package com.cropdeal.inventoryservice.service.implementation;

import com.cropdeal.inventoryservice.dto.InventoryItemDTO;
import com.cropdeal.inventoryservice.exceptions.BadRequestException;
import com.cropdeal.inventoryservice.exceptions.ResourceNotFoundException;
import com.cropdeal.inventoryservice.models.InventoryItemEntity;
import com.cropdeal.inventoryservice.repository.InventoryItemRepository;
import com.cropdeal.inventoryservice.service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Override
    public boolean createInventoryItem(InventoryItemDTO inventoryItemDTO) {
        if(inventoryItemDTO.getCategory()==null||inventoryItemDTO.getCategory().isEmpty() || inventoryItemDTO.getName()==null||inventoryItemDTO.getName().isEmpty()||inventoryItemDTO.getQuantity()<=0||inventoryItemDTO.getPricePerUnit()<0||inventoryItemDTO.getOwnerId()<0){
            throw new BadRequestException("Name, Category, Quantity, and Price cannot be empty, Quantity should be >0, Price should be >=0.");
        }
        /*Get ownerId from dto and match it with id from token, if match then proceed otherwise throw UnauthorizedException, remove ownerId check from above if condition*/
        InventoryItemEntity itemEntity=new InventoryItemEntity();
        BeanUtils.copyProperties(inventoryItemDTO,itemEntity);
        try{
            Optional<InventoryItemEntity> savedItemEntity=Optional.of(inventoryItemRepository.save(itemEntity));
            return true;
        } catch(NullPointerException e){
            return false;
        }
    }

    @Override
    public List<InventoryItemEntity> getAllItems() {
        return inventoryItemRepository.findAll();
    }

    @Override
    public List<InventoryItemEntity> getItemsByOwnerId(Long ownerId) {
        //add check to compare ownerId with id from token, if match then proceed else throw UnauthorizedException
        return inventoryItemRepository.findByOwnerId(ownerId);
    }

    @Override
    public InventoryItemEntity getItemById(String id) {
        Optional<InventoryItemEntity> fetchedItem= inventoryItemRepository.findById(id);
        return fetchedItem.orElseThrow(()->new ResourceNotFoundException("No item in inventory with given id!"));
    }

    @Override
    public boolean updateItemById(String id, InventoryItemDTO inventoryItemDTO) {
        Optional<InventoryItemEntity> fetchedItem=inventoryItemRepository.findById(id);
        //add check to compare ownerId with id from token, if match then proceed else throw UnauthorizedException
        if(fetchedItem.isEmpty())throw new ResourceNotFoundException("Cannot find item with given id!");
        InventoryItemEntity item=fetchedItem.get();
        item.setPricePerUnit(inventoryItemDTO.getPricePerUnit());
        item.setDescription(inventoryItemDTO.getDescription());
        item.setOwnerId(inventoryItemDTO.getOwnerId());
        item.setQuantity(inventoryItemDTO.getQuantity());
        try{
            Optional<InventoryItemEntity> updatedItem=Optional.of(inventoryItemRepository.save(item));
            return true;
        } catch(NullPointerException e){
            return false;
        }
        //return false;
    }

    @Override
    public boolean deleteItemById(String id) {
        Optional<InventoryItemEntity> fetchedItem=inventoryItemRepository.findById(id);
        //check ownerId in fetchedItem with id in token, if match then proceed else thor UnauthorizedException
        if(fetchedItem.isPresent()){
            inventoryItemRepository.deleteById(id);
            return true;
        } else{
            throw new ResourceNotFoundException("");
        }
    }
}
