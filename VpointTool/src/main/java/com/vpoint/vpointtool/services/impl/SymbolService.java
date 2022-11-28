package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.exception.DataNotFoundException;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Symbol;
import com.vpoint.vpointtool.repositories.SymbolRepository;
import com.vpoint.vpointtool.services.ISymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SymbolService implements ISymbolService {

    @Autowired
    private SymbolRepository symbolRepository;

    @Override
    public List<Symbol> findAllByItem(Item item) {
        return symbolRepository.findAllByItemOrderByPoint(item);
    }

    @Override
    public Symbol findSymbolById(Long id) {
        return symbolRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Symbol id: " + id));
    }

    @Override
    public Symbol saveSymbol(Symbol symbol) {
        return symbolRepository.save(symbol);
    }
}
