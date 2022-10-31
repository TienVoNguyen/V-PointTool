package com.vpoint.vpointtool.services;


import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.response.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IUserService {

    User findById(Long id);

    User findByStaffId(String id);

    Page<User> findAll(Pageable pageable);

    User getUserProfile(Long id);
}
