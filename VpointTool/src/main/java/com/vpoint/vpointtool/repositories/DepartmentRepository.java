package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}