package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Long> {
    List<Symbol> findAllByItemOrderByPoint(Item item);

    Optional<Symbol> findById(Long id);
}
