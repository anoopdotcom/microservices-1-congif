package com.Payment.RatingService.Repository;

import com.Payment.RatingService.Model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {
    List<Rating> findByUserId(int userId);
    List<Rating> findByInventoryId(String inventoryId);
}
