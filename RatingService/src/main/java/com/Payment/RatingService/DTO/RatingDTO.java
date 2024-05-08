package com.Payment.RatingService.DTO;

import lombok.Data;

@Data
public class RatingDTO {
    private int userId;
    private String inventoryId;
    private int rating;
    private String review;
}
