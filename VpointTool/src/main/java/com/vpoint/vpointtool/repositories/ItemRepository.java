package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}