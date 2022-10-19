package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.services.IUserService;
import com.vpoint.vpointtool.services.appUser.IAppUserService;
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

    @GetMapping("/list")
    public ResponseEntity<Page<User>> listBlog(@RequestParam("p") Optional<Integer> p){
        Sort sort = Sort.by("full_name").descending();
        Pageable pageable = PageRequest.of(p.orElse(0), 10);
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
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
}
