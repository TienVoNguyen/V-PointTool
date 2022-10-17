package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.models.dto.ChangePassword;
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
import java.util.Optional;


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
        Optional<User> user1 = userService.findByEmail(user.getEmail());
        if (user1.isPresent()){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user1.get().getStaffId(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.findByStaffId1(user1.get().getStaffId()).get();
            return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(),user1.get().getFullName() , userDetails.getAuthorities()));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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
    public ResponseEntity<User> register(@ModelAttribute SignUpForm user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user1 = new User(user.getStaffId(), user.getFullname(), user.getPassword(), user.getEmail(), user.getDepartment(), user.getRole());
        userService.save(user1);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

//    @PostMapping("/repass/{id}")
//    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody ChangePassword changePassword){
//        Optional<User> user = userService.findById(id);
//        String newPassword;
//        String oldPassword = changePassword.getOldPassword();
//        if (!user.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}
