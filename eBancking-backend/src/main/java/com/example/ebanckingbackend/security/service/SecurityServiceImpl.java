package com.example.ebanckingbackend.security.service;

import com.example.ebanckingbackend.security.entities.AppRole;
import com.example.ebanckingbackend.security.entities.AppUser;
import com.example.ebanckingbackend.security.repositories.AppRoleRepository;
import com.example.ebanckingbackend.security.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@NoArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private AppRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public AppUser addNewUser(AppUser appUser) {
        appUser.setId(UUID.randomUUID().toString());
        appUser.setPassword( passwordEncoder.encode( appUser.getPassword() ) );
        return userRepository.save( appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return roleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username,String roleName){
        AppUser appUser = userRepository.findByUsername(username);
        AppRole appRole = roleRepository.findByRoleName(roleName);
        appUser.getRoles().add( appRole );
    }


    @Override
    public AppUser loadUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser>listUsers(){
        return userRepository.findAll();
    }
}