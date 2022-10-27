package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.login.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByStaffId(String staff_id);
    Optional<User> findById(Long id);

    Page<User> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from user where full_name like '%'  ?  '%' order by full_name;")
    List<User> listUser(String fullName);

    @Query(nativeQuery = true, value = "select * from user where department_id = ?;")
    List<User> listUserByCate(int idCate);
}
