package com.CropDeal.AuthService.service;

import com.CropDeal.AuthService.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUser(String email);

}
