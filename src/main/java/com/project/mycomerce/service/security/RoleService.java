package com.project.mycomerce.service.security;

import com.project.mycomerce.domain.security.Role;

public interface RoleService {
    Role findByName(String name);
    void register(Role rol);
}
