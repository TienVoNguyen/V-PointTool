package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymbolRepository extends JpaRepository<Symbol, Long> {
}