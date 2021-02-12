package com.microservices.authserver.controllers;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.authserver.entities.Role;
import com.microservices.authserver.entities.User;
import com.microservices.authserver.services.AccountService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {
    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    @PostAuthorize("hasAnyAuthority('USER')")
    public Collection<User> Users() {
        return accountService.listUsers();
    }

    @PostMapping("/user")
    @PostAuthorize("hasAnyAuthority('ADMIN')")
    public User saveUser(@RequestParam User User) {
        return accountService.addNewUser(User);
    }

    @PostMapping("/role")
    @PostAuthorize("hasAnyAuthority('ADMIN')")
    public Role saveRole(@RequestParam Role Role) {
        return accountService.addNewRole(Role);
    }

    @PostMapping("/addRoleToUser")
    @PostAuthorize("hasAnyAuthority('ADMIN')")
    public void addRoleToUser(@RequestParam RoleUserForm roleToUser) {
        accountService.addRoleToUser(roleToUser.getUserName(), roleToUser.getRoleName());
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationToken = request.getHeader("Authorization");
        if (authorizationToken != null && authorizationToken.startsWith("Bearer")) {
            try {
                String jwt = authorizationToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("mySecret1234");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String userName = decodedJWT.getSubject();
                User user = accountService.loadUserByUserName(userName);
                String jwtAccessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new RuntimeException("Refresh Token Required!");
        }
    }
}