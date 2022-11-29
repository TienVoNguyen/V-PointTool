package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.exception.UserNotFoundException;
import com.vpoint.vpointtool.models.dto.ResponseUser;
import com.vpoint.vpointtool.models.login.Gender;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.response.UserResponse;
import com.vpoint.vpointtool.payload.response.UserProfile;
import com.vpoint.vpointtool.repositories.UserRepository;
import com.vpoint.vpointtool.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> userList(int idCate, String name) {
        return userRepository.userList(idCate, name);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found by ID: "+ id));
    }

    @Override
    public User findByStaffId(String id) {
        System.out.println(id);
        return userRepository.findUserByStaffId(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> listUser(String fullName) {
        return userRepository.listUser(fullName);
    }

    @Override
    public List<User> listUserByCate(int idCate) {
        return userRepository.listUserByCate(idCate);
    }

    @Override
    public Page<ResponseUser> listUserByYear(int year, Pageable pageable){
        return userRepository.listUserByYear(year, pageable);
    };

    @Override
    public User getUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("UserId" + id));
        return user;
    }
}
