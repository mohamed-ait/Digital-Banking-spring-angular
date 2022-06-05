package com.example.ebanckingbackend.security.service;

import com.example.ebanckingbackend.security.entities.AppRole;
import com.example.ebanckingbackend.security.entities.AppUser;

import java.util.List;

public interface SecurityService {

    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser( String username, String roleName);
    AppUser loadUserByUsername( String username);
    List<AppUser> listUsers();
}