package com.microservices.authserver;

import com.microservices.authserver.entities.Role;
import com.microservices.authserver.entities.User;
import com.microservices.authserver.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;


@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AuthserverApplication {
    public static void main(String[] args) {

        SpringApplication.run(AuthserverApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AccountService accountService) {
        return args -> {
            accountService.addNewRole(new Role(null, "USER"));
            accountService.addNewRole(new Role(null, "ADMIN"));
            accountService.addNewRole(new Role(null, "CUSTOMER_MANAGER"));
            accountService.addNewRole(new Role(null, "PRODUCT_MANAGER"));
            accountService.addNewRole(new Role(null, "BILLS_MANAGER"));

            accountService.addNewUser(new User(null, "Hammadi", "010203", new ArrayList<>()));
            accountService.addNewUser(new User(null, "admin", "1234567", new ArrayList<>()));
            accountService.addNewUser(new User(null, "user1", "1234", new ArrayList<>()));
            accountService.addNewUser(new User(null, "user2", "123456", new ArrayList<>()));
            accountService.addNewUser(new User(null, "user3", "010509", new ArrayList<>()));

            accountService.addRoleToUser("Hammadi", "USER");
            accountService.addRoleToUser("admin", "USER");
            accountService.addRoleToUser("admin", "ADMIN");
            accountService.addRoleToUser("user1", "USER");
            accountService.addRoleToUser("user1", "CUSTOMER_MANAGER");
            accountService.addRoleToUser("user2", "USER");
            accountService.addRoleToUser("user2", "PRODUCT_MANAGER");
            accountService.addRoleToUser("user3", "USER");
            accountService.addRoleToUser("user3", "BILLS_MANAGER");
        };
    }
}
