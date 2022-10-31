package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.login.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IAppUserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String name);

    Optional<User> findByStaffId(String name);

    @Query(nativeQuery = true, value = "delete from user_role where user_id = ?1")
    void deleteUser (Long idUser);
}
