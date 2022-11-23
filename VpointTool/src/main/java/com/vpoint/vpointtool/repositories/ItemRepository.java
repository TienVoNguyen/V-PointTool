package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.payload.response.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAll();

    Item findById(long id);

//    @Query(value = "SELECT id, name, start, end, type, point_rule FROM vpoint.item", nativeQuery = true)
//    @Query(value = "SELECT * from Item")
//    List<Item> getAll();
}