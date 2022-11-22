package com.vpoint.vpointtool.services;

import com.vpoint.vpointtool.models.entity.Item;

import java.util.List;

public interface IItemService {
    List<Item> getAll();

    Item findById(long id);

    Item save(Item item);
}
