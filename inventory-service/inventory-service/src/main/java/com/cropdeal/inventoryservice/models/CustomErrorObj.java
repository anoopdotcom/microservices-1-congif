package com.cropdeal.inventoryservice.models;

import lombok.Data;
@Data
public class CustomErrorObj {
    private Integer statusCode;
    private String message;
}
