package com.Payment.RatingService.Model;

import lombok.Data;

@Data
public class CustomErrorObject {
    private Integer statusCode;
    private String message;
}
