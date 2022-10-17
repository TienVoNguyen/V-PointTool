package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.models.entity.User;
import com.vpoint.vpointtool.repositories.UserRepository;
import com.vpoint.vpointtool.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found by ID: "+ id));
    }
}
