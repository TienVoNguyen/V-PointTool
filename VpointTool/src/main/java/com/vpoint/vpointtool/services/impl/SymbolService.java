package com.vpoint.vpointtool.services.impl;

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
        return symbolRepository.findAllByItem(item);
    }

    @Override
    public List<Symbol> findAll() {
        return symbolRepository.findAll();
    }
}
