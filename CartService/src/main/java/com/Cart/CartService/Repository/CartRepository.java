package com.Cart.CartService.Repository;

import com.Cart.CartService.Entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart,String> {
    List<Cart> findAllByUserId(int userId);
}
