package com.project.mycomerce.config.Data;

import com.project.mycomerce.domain.security.Role;
import com.project.mycomerce.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    @Autowired
    private RoleService roleService;
    @PostConstruct
    void init(){
        Role rol1 = new Role();
        rol1.setName("admin");
        Role rol2 = new Role();
        rol2.setName("user");

        roleService.register(rol1);
        roleService.register(rol2);

    }
}
