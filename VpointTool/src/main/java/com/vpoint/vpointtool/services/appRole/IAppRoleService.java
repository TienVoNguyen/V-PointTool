package com.vpoint.vpointtool.services.appRole;

import com.vpoint.vpointtool.models.login.Role;
import com.vpoint.vpointtool.services.IGeneralService;
import org.springframework.stereotype.Service;


public interface IAppRoleService extends IGeneralService<Role> {

    Role findByName(String name);
}
