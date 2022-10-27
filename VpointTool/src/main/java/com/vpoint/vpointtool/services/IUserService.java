package com.vpoint.vpointtool.services;


import com.vpoint.vpointtool.models.login.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User findById(Long id);

    User findByStaffId(String id);

    Page<User> findAll(Pageable pageable);

    List<User> listUser(String fullName);

    List<User> listUserByCate(int idCate);
}
