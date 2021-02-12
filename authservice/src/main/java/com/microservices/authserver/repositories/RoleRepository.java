package com.microservices.authserver.repositories;

import com.microservices.authserver.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long>{
    public Role findByName(String role);
}
