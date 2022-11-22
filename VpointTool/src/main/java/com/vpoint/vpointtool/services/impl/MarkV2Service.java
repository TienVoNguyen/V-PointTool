package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.exception.InputException;
import com.vpoint.vpointtool.models.entity.*;
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
        mark.setPoint(this.getPointDecimal(value, symbol));
        return this.saveMark(mark, symbol, item, user, date).getPoint();
    }

    public float saveText(long id, String value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Mark mark = getMarkText(item, value, user, date);
        if (mark == null) {
            return 0;
        }
        Symbol symbol = getSymbolText(value, item);
        mark.setPoint(symbol.getPoint());
        return this.saveMark(mark, symbol, item, user, date).getPoint();
    }

    public float saveBoolean(long id, Boolean value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Mark mark = getMarkBoolean(item, value, user, date);
        if (mark == null) {
            return 0;
        }
        Symbol symbol = symbolService.findAllByItem(item).get(0);
        mark.setValue(String.valueOf(value));
        mark.setPoint(symbol.getPoint());
        return this.saveMark(mark, symbol, item, user, date).getPoint();
    }

    public float savePoint(long id, Float value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Mark mark = createOrReplaceOrDeleteMark(item, value, user, date);
        if (mark == null) {
            return 0;
        }
        Symbol symbol = symbolService.findAllByItem(item).get(0);
        mark.setPoint(getPoint(item, value, user , date));
        mark.setValue(String.valueOf(value));
        return this.saveMark(mark, symbol, item, user, date).getPoint();
    }

    private Float getPoint(Item item, Float value, User user, LocalDate date) {
        if (item.getPointRule() == null) {
            return value;
        } else if (item.getPointRule().equals(PointRule.MONTH)) {
            if (item.getStart() == null) {
                if (value <= item.getEnd()) {
                    return value;
                } else {
                    throw new InputException(item.getName());
                }
            } else if (item.getEnd() == null) {
                if (value >= item.getStart()) {
                    return value;
                } else {
                    throw new InputException(item.getName());
                }
            } else if (value >= item.getStart() && value <= item.getEnd()) {
                return value;
            } else {
                throw new InputException(item.getName());
            }
        } else {
            if (value < item.getStart()) {
                throw new InputException(item.getName());
            }
            float tmp = sumPoint(item, user, date) != null ? sumPoint(item, user, date) : 0;
            if (tmp >= item.getEnd()) {
                return 0F;
            } else {
                float point = item.getEnd() - tmp;
                return Math.min(point, value);
            }
        }
    }

    private Float sumPoint(Item item, User user, LocalDate date) {
        return markRepository.getPointImproveYear(user, item, date.getYear());
    }

    private Mark getMark(Item item, User user, LocalDate date) {
        Optional<Mark> markUpdate = markRepository.findByItemAndDateAndUser(item, date, user);
        return markUpdate.orElse(null);
    }

    private Mark getMarkBoolean(Item item, Boolean value, User user, LocalDate date) {
        System.out.println("11: " + value);
        Mark mark = getMark(item, user, date);
        if ((value == null || !value) && mark == null) {
            return null;
        } else if (mark == null) {
            return new Mark();
        } else if (value == null || !value){
            markRepository.delete(mark);
            return null;
        } else {
            mark.setValue(String.valueOf(true));
            return mark;
        }
    }

    private Mark getMarkText(Item item, String value, User user, LocalDate date) {
        Mark mark = getMark(item, user, date);
        if (value == null && mark == null) {
            return null;
        } else if (mark == null) {
            return new Mark();
        } else if (value == null){
            markRepository.delete(mark);
            return null;
        } else {
            mark.setValue(value);
            return mark;
        }
    }

    private void deleteMarkRuleYear(Item item, User user, LocalDate date) {
        Mark mark = getMark(item, user, date);
        float tmp = mark.getPoint();
        markRepository.delete(mark);
        List<Mark> marks = markRepository.findMarksByUserAndItemInYear(item, user, date.getYear());
        for (Mark m : marks) {
            if (tmp > 0) {
                float value = Float.parseFloat(m.getValue());
                if (m.getPoint() < value && sumPoint(item, user, date) < item.getEnd()) {
                    float newPoint =  Math.min(tmp, value - m.getPoint());
                    tmp -= newPoint;
                    m.setPoint(newPoint + m.getPoint());
                    markRepository.save(m);
                } else {
                    break;
                }
            }

        }
    }

    private Mark createOrReplaceOrDeleteMark(Item item, Float value, User user, LocalDate date) {
        Mark mark = getMark(item, user, date);
        if (value == null && mark == null) {
            return null;
        } else if (mark == null) {
            return new Mark();
        } else if (value == null){
            if (item.getPointRule().equals(PointRule.YEAR)) {
                deleteMarkRuleYear(item, user, date);
            } else
                markRepository.delete(mark);
            return null;
        } else {
            mark.setValue(String.valueOf(value));
            return mark;
        }
    }

    private Mark saveMark(Mark mark ,Symbol symbol, Item item, User user, LocalDate date) {
        mark.setItem(item);
        mark.setSymbol(symbol);
        mark.setUser(user);
        mark.setDate(date);
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
            if (compareSymbolD(value, symbol, item.getType())) {
                tmp = symbol;
            }
        }
        if (tmp == null) {
            throw new InputException(item.getName());
        }
        return tmp;
    }

    private Symbol getSymbolText(String value, Item item) {
        List<Symbol> symbols = symbolService.findAllByItem(item);
        Symbol tmp = null;
        for (Symbol symbol : symbols) {
            if (value.equals(symbol.getSign())) {
                tmp = symbol;
            }
        }
        if (tmp == null) {
            throw new InputException(item.getName());
        }
        return tmp;
    }

    private boolean compareSymbolD(Float value, Symbol symbol, Type type) {
        if (type.equals(Type.DECIMALBIGGER)) {
            return compareSymbolDecimal(value, symbol);
        } else if (type.equals(Type.DECIMALBIGGEREQUAL)) {
            return compareSymbolDecimalBiggerEqual(value, symbol);
        } else {
            return compareSymbolDecimalLowerEqual(value, symbol);
        }
    }

    private boolean compareSymbolDecimalLowerEqual(Float value, Symbol symbol) {
        if (symbol.getCompare() != null) {
            return value.equals(symbol.getStart());
        }
        else if (symbol.getEnd() == null){
            return value > symbol.getStart();
        } else if (symbol.getStart() == null) {
            return value <= symbol.getEnd();
        } else {
            return (value > symbol.getStart() && value <= symbol.getEnd());
        }
    }

    private boolean compareSymbolDecimalBiggerEqual(Float value, Symbol symbol) {
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

}
