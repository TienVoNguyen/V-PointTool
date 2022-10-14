package com.vpoint.vpointtool.services.appUser;


import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.services.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface IAppUserService extends IGeneralService<User>, UserDetailsService {

    User findByName(String name);
}
