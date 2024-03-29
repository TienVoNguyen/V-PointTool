package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.models.entity.Symbol;
import com.vpoint.vpointtool.models.login.Gender;
import com.vpoint.vpointtool.models.dto.PointSum;
import com.vpoint.vpointtool.models.dto.ResponseUser;
import com.vpoint.vpointtool.models.dto.Sum;
import com.vpoint.vpointtool.models.dto.Year;
import com.vpoint.vpointtool.models.entity.Mark;

import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.response.UserProfile;
import com.vpoint.vpointtool.payload.response.UserResponse;
import com.vpoint.vpointtool.services.ISymbolService;
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

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
public class    UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private MarkService markService;

    @Autowired
    private ISymbolService symbolService;

    @GetMapping("/list")
    public ResponseEntity<Page<User>> listBlog(@RequestParam("p") Optional<Integer> p, @RequestParam("size" ) int size){
        Sort sort = Sort.by("full_name").descending();
        Pageable pageable = PageRequest.of(p.orElse(0), size);
        Page<User> userList = userService.findAll(pageable);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/listByYear")
    public ResponseEntity<List<Sum>> listByYear(@RequestParam("year") int year){
        return new ResponseEntity<>(markService.getSum(year), HttpStatus.OK);
    }

    @GetMapping("/adminGetYear")
    public ResponseEntity<List<Year>> AdminGetYear(){
        List<Year> year = markService.getYear();
        return new ResponseEntity<>(year, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteBlog(@PathVariable Long id){
        Optional<User> user = appUserService.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            Mark[] marks = user.get().getMarks().toArray(new Mark[0]);
            for (int i = 0; i < marks.length; i++) {
                markService.remove(marks[i].getId());
            }
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

    @GetMapping("/getUserByName")
    public ResponseEntity<List<User>> listBlogByName(@RequestParam("fullName") String fullName){
        List<User> userList = userService.listUser(fullName);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/getUserByCateAndName")
    public ResponseEntity<List<User>> listUserByCateIdAndName(@RequestParam("CateId") int CateId,
                                                              @RequestParam("fullName") String fullName){
        List<User> userList = userService.userList(CateId, fullName);
        return new ResponseEntity<>(userList, HttpStatus.OK);

    }

    @GetMapping("/getUserByCate")
    public ResponseEntity<List<User>> listBlogByCateId(@RequestParam("CateId") int CateId){
        List<User> userList = userService.listUserByCate(CateId);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/getAllSymbol")
    public ResponseEntity<List<Symbol>> symbolList(){
        List<Symbol> symbols = symbolService.findAll();
        return new ResponseEntity<>(symbols, HttpStatus.OK);
    }

}
