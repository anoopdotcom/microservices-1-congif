package com.Payment.RatingService.Service;

import com.Payment.RatingService.DTO.RatingDTO;
import com.Payment.RatingService.Model.Rating;

import java.util.List;

public interface RatingService {

    boolean createRating(RatingDTO ratingDTO);

    List<Rating> getAllRatings();

    List<Rating> getAllRatingsByUserId(int userId);

    List<Rating> getAllRatingByInventoryId(String inventoryId);

    boolean updateRatingById(String id,RatingDTO ratingDTO);

    boolean deleteRatingById(String id);

}
