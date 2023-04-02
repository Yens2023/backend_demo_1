package com.project.mycomerce.service.security.Impl;

import com.project.mycomerce.domain.security.Role;
import com.project.mycomerce.repository.security.RoleRepository;
import com.project.mycomerce.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    public void register(Role rol){

        if(!roleRepository.existsByName(rol.getName())){
            roleRepository.save(rol);
        }
    }
}
