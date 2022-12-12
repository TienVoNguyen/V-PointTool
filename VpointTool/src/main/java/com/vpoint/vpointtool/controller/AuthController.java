package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.models.dto.AdminChangePass;
import com.vpoint.vpointtool.models.dto.ChangePassword;
import com.vpoint.vpointtool.models.dto.JwtResponse;
import com.vpoint.vpointtool.models.entity.Department;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.login.Role;
import com.vpoint.vpointtool.models.login.User;

import com.vpoint.vpointtool.payload.response.MessageResponse;

import com.vpoint.vpointtool.payload.request.SignUpForm;
import com.vpoint.vpointtool.services.appRole.IAppRoleService;
import com.vpoint.vpointtool.services.appUser.AppUserService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> user1 = userService.findByEmail(user.getEmail());
        if (user1.isPresent() && user1.get().isStatus() == true){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user1.get().getStaffId(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.findByStaffId1(user1.get().getStaffId()).get();
            return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(),user1.get().getFullName(), currentUser.isStatus() , userDetails.getAuthorities()));
        }
        else if (user1.isPresent() && user1.get().isStatus() == false) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User has locked");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
        return new ResponseEntity<>(departmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getUser(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @ModelAttribute SignUpForm user, BindingResult result) {
        User[] users = appUserService.findAll().toArray(new User[0]);
        boolean checkStaff = true;
        boolean checkEmail = true;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getStaffId().equals(user.getStaffId())){
                checkStaff = false;
                break;
            }
        }
        for (int i = 0; i < users.length; i++) {
            if (users[i].getEmail().equals(user.getEmail())){
                checkEmail = false;
                break;
            }
        }
        if (!checkEmail || !checkStaff) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user1 = new User(user.getStaffId(), user.getFullname(), user.getPassword(), user.getEmail(), user.getDepartment(), user.getRole(), user.getPhone(), user.getGender());
        List<Mark> listMark = new ArrayList<>();
        user1.setMarks(listMark);
        user1.setStatus(true);
        userService.save(user1);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PutMapping("/lockUser/{userId}")
    public ResponseEntity<User> LockUser(@PathVariable Long userId){
        User user = userService.findById(userId).get();
        user.setStatus(false);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/unlockUser/{userId}")
    public ResponseEntity<User> unlockUser(@PathVariable Long userId){
        User user = userService.findById(userId).get();
        user.setStatus(true);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findByIdUser/{userId}")
    public ResponseEntity<User> findByIdUser(@PathVariable Long userId){
        Optional<User> user = userService.findById(userId);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Role> roleSet = user.get().getRole().stream().collect(Collectors.toList());
        String name = null;
        for (int i = 0; i < roleSet.size(); i++) {
            if (roleSet.get(0).getName().equals("ROLE_ADMIN")){
                name = "Admin";
            }  else {
                name = "Người dùng";
            }
        }
        user.get().setCreateBy(name);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @Autowired
    private IAppUserService appUserService;

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateProfile(@Valid @PathVariable Long userId, @ModelAttribute SignUpForm signUpForm
            , BindingResult result) {
        Optional<User> user = userService.findById(userId);

        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        user.get().setId(userId);
        user.get().setStaffId(signUpForm.getStaffId());
        user.get().setFullName(signUpForm.getFullname());
        user.get().setEmail(signUpForm.getEmail());
        user.get().setPhone(signUpForm.getPhone());
        user.get().setDepartment(signUpForm.getDepartment());
        user.get().setRole(signUpForm.getRole());
        userService.saveForm(user.get());
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/userChangePassword/{id}")
    public ResponseEntity<User> changePassword(@PathVariable Long id, @RequestBody ChangePassword changePassword) {
        Optional<User> user = this.userService.findById(id);
        String newPassword;
        String oldPassword = changePassword.getOldPassword();
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (passwordEncoder.matches(oldPassword, user.get().getPassword())) {
                if (changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
                    newPassword = changePassword.getNewPassword();
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        user.get().setPassword(newPassword);
        user.get().setId(id);
        this.userService.save(user.get());
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/adminChangePassword/{id}")
    public ResponseEntity<User> AdminChangePassword(@PathVariable Long id, @RequestBody AdminChangePass changePassword) {
        Optional<User> user = this.userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!Objects.equals(changePassword.getNewPassword(), changePassword.getConfirmNewPass())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.get().setPassword(changePassword.getNewPassword());
        user.get().setId(id);
        this.userService.save(user.get());
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

}
