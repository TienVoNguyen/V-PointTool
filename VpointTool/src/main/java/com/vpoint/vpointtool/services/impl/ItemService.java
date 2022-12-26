package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.exception.DataNotFoundException;
import com.vpoint.vpointtool.exception.InputException;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.PointRule;
import com.vpoint.vpointtool.models.entity.Symbol;
import com.vpoint.vpointtool.models.entity.Type;
import com.vpoint.vpointtool.payload.request.MarkDecimal;
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
        return itemRepository.save(item);
//        return null;
    }

    @Override
    public List<Item> getAllRule() {
        return itemRepository.findAll();
    }

    @Override
    public void editMark(Item item, List<MarkDecimal> markDecimals) {
        if (item.getType().equals(Type.BOOLEAN)) {
            this.editMarkBoolean(markDecimals);
        } else if (item.getType().equals(Type.TEXT)) {
            this.editMarkText(item, markDecimals);
        } else {
            this.editMarkDecimal(item, markDecimals);
        }
    }

    private void editMarkBoolean(List<MarkDecimal> markDecimals) {
        for (MarkDecimal tmp : markDecimals) {
            Symbol symbol = symbolService.findSymbolById(tmp.getId());
            symbol.setPoint(tmp.getPoint());
            symbolService.saveSymbol(symbol);
        }
    }

    @Override
    @Transactional
    public void editMarkDecimal(Item item, List<MarkDecimal> markDecimals) {
//        validateDecimal(markDecimals);
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

            if (item.getPointRule().equals(PointRule.YEAR) && markDecimal.getEnd() != null) {
                item.setEnd(markDecimal.getEnd());
                itemRepository.save(item);
                break;
            }

            if (markDecimal.getPoint() != null && symbol.getPoint() != null) {
                symbol.setPoint(markDecimal.getPoint());
            } else if (symbol.getPoint() != null && markDecimal.getPoint() == null) {
                throw new InputException("Symbol point");
            }
            symbolService.saveSymbol(symbol);
        }
    }

    private void validateDecimal(List<MarkDecimal> markDecimals) {
//        List<MarkDecimal> decimals = new java.util.ArrayList<>(markDecimals.stream()
//                .sorted(Comparator.comparing(MarkDecimal::getPoint))
//                .toList());
//        for (int i = 0; i < decimals.size() - 1; i++) {
//            Symbol symbol = symbolService.findSymbolById(decimals.get(i).getId());
//            if (symbol.getCalculation() != null && (symbol.getCalculation().equals(Calculation.Multiplier) ||
//                    symbol.getCompare())) {
//                decimals.remove(i);
//            }
//        }
//        for (int i = 0; i < decimals.size() - 1; i ++) {
//            if (decimals.get(i).getStart() == null && decimals.get(i).getEnd() != null) {
//                if (decimals.get(i).getEnd() > decimals.get(i+1).getStart()) {
//                    throw new InputException("Mark");
//                }
//            } else if (decimals.get(i).getStart() != null && decimals.get(i).getEnd() != null) {
//                if (decimals.get(i).getStart() > decimals.get(i).getEnd()) {
//                    throw new InputException("Mark");
//                } else if (decimals.get(i).getEnd() > decimals.get(i + 1).getStart()) {
//                    throw new InputException("Mark");
//                }
//            } else if (decimals.get(i).getStart() != null && decimals.get(i).getEnd() == null) {
//                if (decimals.get(i).getEnd() < decimals.get(i-1).getEnd()) {
//                    throw new InputException("Mark");
//                }
//            }
//        }
//        for (int i = 0; i < markDecimals.size(); i++) {
//            if (markDecimals.get(i).getStart() != null && markDecimals.get(i).getEnd() != null) {
//                if (markDecimals.get(i))
//            }
//        }
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
