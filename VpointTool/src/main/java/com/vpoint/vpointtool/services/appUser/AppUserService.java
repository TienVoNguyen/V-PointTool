package com.vpoint.vpointtool.services.appUser;


import com.vpoint.vpointtool.models.dto.UserPrinciple;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.repositories.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AppUserService implements IAppUserService{

    @Autowired
    private IAppUserRepo appUserRepo;

    @Override
    public List<User> findAll() {
        return appUserRepo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return appUserRepo.findById(id);
    }

    @Override
    public User save(User appUser) {
        return appUserRepo.save(appUser);
    }

    @Override
    public void remove(Long id) {
        appUserRepo.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String name) {
        return appUserRepo.findByEmail(name);
    }

    @Override
    public Optional<User> findByStaffId1(String name) {
        return appUserRepo.findByStaffId(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = appUserRepo.findByStaffId(username);
        if (userOptional.isPresent()){
            return UserPrinciple.build(userOptional.get());
        }
        return null;
    }

}
