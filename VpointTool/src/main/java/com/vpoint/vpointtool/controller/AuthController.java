package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.models.dto.JwtResponse;
import com.vpoint.vpointtool.models.entity.Department;
import com.vpoint.vpointtool.models.login.Role;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.models.request.SignUpForm;
import com.vpoint.vpointtool.services.appRole.IAppRoleService;
import com.vpoint.vpointtool.services.appUser.IAppUserService;
import com.vpoint.vpointtool.services.department.IDepartmentService;
import com.vpoint.vpointtool.services.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private IAppUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByName(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(),userDetails.getUsername() , userDetails.getAuthorities()));
    }

    @Autowired
    private IAppRoleService appRoleService;

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/getAllRole")
    public ResponseEntity<List<Role>> getRole(){
        return new ResponseEntity<>(appRoleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getAllDepartment")
    public ResponseEntity<List<Department>> getDepartment(){
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getUser(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<User> register( @RequestBody SignUpForm user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user1 = new User(user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail(), user.getDepartment(), user.getRoles());
        userService.save(user1);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

}
