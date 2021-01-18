package com.spring.esouk.entity.dao;

import com.spring.esouk.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRole(String role);
}
