package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.login.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IAppUserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);
}
