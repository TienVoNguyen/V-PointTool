package com.vpoint.vpointtool.services;


import com.vpoint.vpointtool.models.login.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserService {

    User findById(Long id);

    Page<User> findAll(Pageable pageable);
}
