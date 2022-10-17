package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Long> {
}