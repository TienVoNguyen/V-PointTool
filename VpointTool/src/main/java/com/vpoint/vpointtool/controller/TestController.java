package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Type;
import com.vpoint.vpointtool.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class TestController {

    @Autowired
    private IItemService itemService;

    @GetMapping("/test/{id}")
    public Item test(@PathVariable("id") Long id) {
        Item item = itemService.findById(id);
        item.setType(Type.POINT);
        return itemService.save(item);
    }
}
