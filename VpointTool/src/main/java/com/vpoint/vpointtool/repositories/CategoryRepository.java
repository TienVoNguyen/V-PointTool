package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}