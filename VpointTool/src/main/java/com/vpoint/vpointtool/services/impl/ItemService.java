package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.payload.response.Rule;
import com.vpoint.vpointtool.repositories.ItemRepository;
import com.vpoint.vpointtool.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item save(Item item) {
//        return itemRepository.save(item);
        return null;
    }

    @Override
    public List<Item> getAllRule() {
        return itemRepository.findAll();
    }
}
