package com.CropDeal.AuthService.entity;

public enum RoleType {
    FARMER("ROLE_FARMER"),
    DEALER("ROLE_DEALER"),
    ADMIN("ROLE_ADMIN");

    private String value;

    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
