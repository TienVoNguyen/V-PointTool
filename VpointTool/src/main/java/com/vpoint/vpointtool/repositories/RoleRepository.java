package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}