package com.vpoint.vpointtool.services;

import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Symbol;

import java.util.List;

public interface ISymbolService {
    List<Symbol> findAllByItem(Item item);

    Symbol findSymbolById(Long id);

    Symbol saveSymbol(Symbol symbol);
    List<Symbol> findAll();
}
