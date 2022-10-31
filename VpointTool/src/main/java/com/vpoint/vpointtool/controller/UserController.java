package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.models.login.Gender;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.response.UserProfile;
import com.vpoint.vpointtool.payload.response.UserResponse;
import com.vpoint.vpointtool.services.IUserService;
import com.vpoint.vpointtool.services.appUser.IAppUserService;
import com.vpoint.vpointtool.services.impl.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private MarkService markService;

    @GetMapping("/list")
    public ResponseEntity<Page<User>> listBlog(@RequestParam("p") Optional<Integer> p){
        Sort sort = Sort.by("full_name").descending();
        Pageable pageable = PageRequest.of(p.orElse(0), 10);
        Page<User> userList = userService.findAll(pageable);

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteBlog(@PathVariable Long id){
        Optional<User> blogOptional = appUserService.findById(id);
        if (!blogOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            appUserService.remove(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        UserResponse userResponse = new UserResponse(user.getStaffId(), user.getFullName(), user.getDepartment().getName());
        return new ResponseEntity<>(userResponse,  HttpStatus.OK);
    }

    @GetMapping(value = "/user/profile/{id}")
    public ResponseEntity<?> profileUser(@PathVariable("id") Long id) {
        User user = userService.getUserProfile(id);
        String gender = user.getGender() != null ? user.getGender().name() : null;
        user.setGender(Gender.MALE);
        UserProfile userProfile = new UserProfile(
                user.getId(),
                user.getDepartment().getName(),
                user.getFullName(),
                user.getStaffId(),
                user.getEmail(),
                gender,
                user.getPhone());
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}
