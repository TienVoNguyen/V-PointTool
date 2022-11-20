package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.exception.InputException;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.entity.Symbol;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.request.AddMarkUser;
import com.vpoint.vpointtool.repositories.ItemRepository;
import com.vpoint.vpointtool.repositories.MarkRepository;
import com.vpoint.vpointtool.repositories.UserRepository;
import com.vpoint.vpointtool.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MarkV2Service {
    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private IItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SymbolService symbolService;

    public float saveDecimal(long id, Float value, User user, LocalDate date){
        Item item = itemService.findById(id);
        Mark mark = createOrReplaceOrDeleteMark(item, value, user, date);
        if (mark == null) {
            return 0;
        }
        Symbol symbol = getSymbol(value, item);
        mark.setValue(String.valueOf(value));
        return this.saveMark(mark, symbol, item, value, user, date).getPoint();
    }

    public float saveDecimalBSC(long id, Float value, User user, LocalDate date){
        Item item = itemService.findById(id);
        Mark mark = createOrReplaceOrDeleteMark(item, value, user, date);
        if (mark == null) {
            return 0;
        }
        Symbol symbol = getSymbolBSC(value, item);
        mark.setValue(String.valueOf(value));
        return this.saveMark(mark, symbol, item, value, user, date).getPoint();
    }

    private Mark createOrReplaceOrDeleteMark(Item item, Float value, User user, LocalDate date) {
        Optional<Mark> markUpdate = markRepository.findByItemAndDateAndUser(item, date, user);
        Mark mark = new Mark();
        if (markUpdate.isPresent()) {
            mark = markUpdate.get();
            if (value == null) {
                markRepository.delete(mark);
                return null;
            }
        }
        if (value == null) {
            return null;
        }
        return mark;
    }

    private Mark saveMark(Mark mark ,Symbol symbol, Item item, Float value, User user, LocalDate date) {
        mark.setItem(item);
        mark.setSymbol(symbol);
        mark.setUser(user);
        mark.setDate(date);
        mark.setPoint(this.getPointDecimal(value, symbol));
        return markRepository.save(mark);
    }

    private float getPointDecimal(Float value, Symbol symbol) {
        if (symbol.getCalculation() != null) {
            if (symbol.getPoint() < 0)
                return (symbol.getEnd() - value) * symbol.getPoint();
            else {
                return (value - symbol.getStart()) * symbol.getPoint();
            }
        } else {
            return symbol.getPoint();
        }
    }

    private Symbol getSymbol(Float value, Item item) {
        List<Symbol> symbols = symbolService.findAllByItem(item);
        Symbol tmp = null;
        for (Symbol symbol : symbols) {
            if (compareSymbolDecimal(value , symbol)) {
                tmp = symbol;
            }
        }
        if (tmp == null) {
            throw new InputException(item.getName());
        }
        return tmp;
    }
    private Symbol getSymbolBSC(Float value, Item item) {
        List<Symbol> symbols = symbolService.findAllByItem(item);
        Symbol tmp = null;
        for (Symbol symbol : symbols) {
            if (compareSymbolDecimalBSC(value , symbol)) {
                tmp = symbol;
            }
        }
        if (tmp == null) {
            throw new InputException(item.getName());
        }
        return tmp;
    }


    private boolean compareSymbolDecimal(Float value, Symbol symbol) {
        if (symbol.getCompare() != null) {
            return value.equals(symbol.getStart());
        } else if (symbol.getEnd() == null){
            return value >= symbol.getStart();
        } else if (symbol.getStart() == null) {
            return value <= symbol.getEnd();
        } else {
            return (value > symbol.getStart() && value < symbol.getEnd());
        }
    }

    private boolean compareSymbolDecimalBSC(Float value, Symbol symbol) {
        if (symbol.getCompare() != null) {
            return value.equals(symbol.getStart());
        }
        else if (symbol.getEnd() == null){
            return value >= symbol.getStart();
        } else if (symbol.getStart() == null) {
            return value < symbol.getEnd();
        } else {
            return (value >= symbol.getStart() && value < symbol.getEnd());
        }
    }
}
