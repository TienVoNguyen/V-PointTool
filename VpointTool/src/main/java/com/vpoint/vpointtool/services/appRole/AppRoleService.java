package com.vpoint.vpointtool.services.appRole;


import com.vpoint.vpointtool.models.login.Role;
import com.vpoint.vpointtool.repositories.IAppRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AppRoleService implements IAppRoleService{

    @Autowired
    private IAppRoleRepo appRoleRepo;

    @Override
    public List<Role> findAll() {
        return appRoleRepo.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return appRoleRepo.findById(id);
    }

    @Override
    public Role save(Role appRole) {
        return appRoleRepo.save(appRole);
    }

    @Override
    public void remove(Long id) {
    appRoleRepo.deleteById(id);
    }

    @Override
    public Role findByName(String name) {
        return appRoleRepo.findByName(name);
    }
}
