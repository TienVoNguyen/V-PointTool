package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.exception.DataNotFoundException;
import com.vpoint.vpointtool.exception.InputException;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Symbol;
import com.vpoint.vpointtool.payload.request.MarkDecimal;
import com.vpoint.vpointtool.payload.response.Rule;
import com.vpoint.vpointtool.repositories.ItemRepository;
import com.vpoint.vpointtool.services.IItemService;
import com.vpoint.vpointtool.services.ISymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ISymbolService symbolService;

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

    @Override
    @Transactional
    public void editMarkDecimal(Item item, List<MarkDecimal> markDecimals) {
        List<Symbol> symbols = symbolService.findAllByItem(item);
        for (MarkDecimal markDecimal : markDecimals) {
            Symbol symbol = symbolService.findSymbolById(markDecimal.getId());
            if (!symbols.contains(symbol)) {
                throw new DataNotFoundException("Symbol");
            }

            if (symbol.getStart() == null) {
                symbol.setEnd(markDecimal.getEnd());
            } else if (symbol.getEnd() == null) {
                symbol.setStart(markDecimal.getStart());
            } else {
                symbol.setEnd(markDecimal.getEnd());
                symbol.setStart(markDecimal.getStart());
            }

            if (markDecimal.getPoint() != null) {
                symbol.setPoint(markDecimal.getPoint());
            } else {
                throw new DataNotFoundException("Symbol point point");
            }
            symbolService.saveSymbol(symbol);
        }
    }

    @Override
    @Transactional
    public void editMarkText(Item item, List<MarkDecimal> markDecimals) {
        List<Symbol> symbols = symbolService.findAllByItem(item);
        for (MarkDecimal markDecimal : markDecimals) {
            Symbol symbol = symbolService.findSymbolById(markDecimal.getId());
            if (!symbols.contains(symbol)) {
                throw new DataNotFoundException("Symbol");
            }

            if (markDecimal.getPoint() != null) {
                symbol.setPoint(markDecimal.getPoint());
            } else {
                throw new DataNotFoundException("Symbol point");
            }
            symbolService.saveSymbol(symbol);
        }
    }

//    private void checkSymbolExits(List<Symbol>)

}
