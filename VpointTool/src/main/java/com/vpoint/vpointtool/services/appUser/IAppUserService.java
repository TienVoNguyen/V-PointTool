package com.vpoint.vpointtool.services.appUser;


import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.services.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IAppUserService extends IGeneralService<User>, UserDetailsService {

    Optional<User> findByEmail(String name);

    Optional<User> findByStaffId1(String name);

    User saveUser(User user);


}
