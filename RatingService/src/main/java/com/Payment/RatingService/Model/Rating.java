package com.Payment.RatingService.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Rating {
    @Id
    private String id;
    @NotNull
    private String inventoryId;
    @NotNull
    private int userId;
    @NotNull
    @Min(1)
    @Max(5)
    private int rating;
    private String review;
}
