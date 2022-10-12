package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}