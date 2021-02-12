package com.microservices.authserver.services;

import com.microservices.authserver.entities.Role;
import com.microservices.authserver.entities.User;
import com.microservices.authserver.repositories.RoleRepository;
import com.microservices.authserver.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private UserRepository UserRepository;
    private RoleRepository RoleRepository;
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(UserRepository UserRepository, RoleRepository RoleRepository, PasswordEncoder passwordEncoder) {
        this.UserRepository = UserRepository;
        this.RoleRepository = RoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addNewUser(User User) {
        String password = User.getPassword();
        User.setPassword(passwordEncoder.encode(password));
        return UserRepository.save(User);
    }

    @Override
    public Role addNewRole(Role Role) {
        return RoleRepository.save(Role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User User = UserRepository.findByUsername(userName);
        Role Role = RoleRepository.findByRoleName(roleName);
        User.getRoles().add(Role);
    }

    @Override
    public User loadUserByUserName(String userName) {
        return UserRepository.findByUsername(userName);
    }

    @Override
    public Collection<User> listUsers() {
        return UserRepository.findAll();
    }
}