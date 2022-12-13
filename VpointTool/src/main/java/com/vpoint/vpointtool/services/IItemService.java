package com.vpoint.vpointtool.services;

import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.payload.request.MarkDecimal;
import com.vpoint.vpointtool.payload.response.Rule;

import java.util.List;

public interface IItemService {
    List<Item> getAll();

    Item findById(long id);

    Item save(Item item);

    List<Item> getAllRule();

    void editMark(Item item, List<MarkDecimal> markDecimals);

    void editMarkDecimal(Item item, List<MarkDecimal> markDecimals);

    void editMarkText(Item item, List<MarkDecimal> markDecimals);
}
