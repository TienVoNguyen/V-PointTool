package com.vpoint.vpointtool.services;

import com.vpoint.vpointtool.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IUserService {

    User findById(Long id);

    User findByStaffId(String id);
}
