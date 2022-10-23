package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.login.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRoleRepo extends JpaRepository<Role, Long> {
        Role findByName(String name);
}
