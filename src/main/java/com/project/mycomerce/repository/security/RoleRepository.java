package com.project.mycomerce.repository.security;
import com.project.mycomerce.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>  {
    Role findRoleByName(String name);
    Boolean existsByName(String name);
}
