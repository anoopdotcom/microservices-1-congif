package com.CropDeal.AuthService.service.implementation;

import com.CropDeal.AuthService.entity.User;
import com.CropDeal.AuthService.repository.UserRepository;
import com.CropDeal.AuthService.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

}
