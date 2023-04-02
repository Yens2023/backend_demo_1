package com.project.mycomerce.service.security;

import com.project.mycomerce.domain.security.User;
import com.project.mycomerce.dto.user.CreateUserDto;
import com.project.mycomerce.dto.user.UpdateUserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
// spring repository methods

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(CreateUserDto createUser);
    User update(UpdateUserDto updateUser);

    void deleteById(Long id);


    //custom methods
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
