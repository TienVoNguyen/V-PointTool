package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SymbolRepository extends JpaRepository<Symbol, Long> {
    Symbol findSymbolByItemAndName(Item item, String name);

    Symbol findSymbolByName(String name);

    Symbol findSymbolByItem(Item item);

    List<Symbol> findSymbolsByItem(Item item);
}