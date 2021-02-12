package com.microservices.authserver.services;

import com.microservices.authserver.entities.Role;
import com.microservices.authserver.entities.User;

import java.util.Collection;

public interface AccountService {
    User addNewUser(User appUser);
    Role addNewRole(Role role);
    void addRoleToUser(String userName, String roleName);
    User loadUserByUserName(String userName);
    Collection<User> listUsers();
}
