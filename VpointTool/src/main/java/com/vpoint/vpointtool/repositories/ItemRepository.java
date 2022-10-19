package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAll();

    Optional<Item> findById(long id);
}