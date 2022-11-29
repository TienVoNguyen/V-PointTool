package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.models.entity.Category;
import com.vpoint.vpointtool.models.entity.Department;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.models.request.CategoryForm;
import com.vpoint.vpointtool.payload.response.ResponseCate;
import com.vpoint.vpointtool.services.ICategoriesService;
import com.vpoint.vpointtool.services.department.IDepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<Department> register(@RequestBody CategoryForm categoryForm) {
        Department department = new Department(categoryForm.getName().trim());
        return new ResponseEntity<>(departmentService.save(department), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> edit(@RequestBody Department department, @PathVariable Long id) {
        Department department1 = departmentService.findById(id).get();
        department1.setId(id);
        department1.setName(department.getName());
        return new ResponseEntity<>(departmentService.save(department1), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Department>> listBlog(@RequestParam("p") Optional<Integer> p, @RequestParam("size" ) int size){
        Pageable pageable = PageRequest.of(p.orElse(0), size);
        return new ResponseEntity<>(departmentService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findDpm(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/listQuantity")
    public ResponseEntity<List<ResponseCate>> listQuantity(){
        return new ResponseEntity<>(departmentService.listDpm(), HttpStatus.OK);
    }

    @GetMapping("/getDpmByName")
    public ResponseEntity <List<Department>> listUserByCateIdAndName(@RequestParam("fullName") String fullName){
        return new ResponseEntity<>(departmentService.findByDpmName(fullName), HttpStatus.OK);
    }

}
