package com.vpoint.vpointtool.services.appUser;


import com.vpoint.vpointtool.models.dto.UserPrinciple;
import com.vpoint.vpointtool.models.login.Role;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.repositories.IAppRoleRepo;
import com.vpoint.vpointtool.repositories.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService implements IAppUserService{

    @Autowired
    private IAppUserRepo appUserRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


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
        String password = appUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);//Mã hóa pass của người dùng
        appUser.setPassword(encodePassword);
        return appUserRepo.save(appUser);
    }

    public User saveForm(User appUser) {
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
    public User saveUser(User user) {
        user.setPassword(user.getPassword());
        return appUserRepo.save(user);
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
