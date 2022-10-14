package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.login.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
