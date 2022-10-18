package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {


    @Query(nativeQuery = true, value = "select * from mark where user_id = ?;")
    List<Mark> getMarkByIdUser(Long idUser);
}
