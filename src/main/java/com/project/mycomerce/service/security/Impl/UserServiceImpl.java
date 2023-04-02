package com.project.mycomerce.service.security.Impl;

import com.project.mycomerce.domain.security.User;
import com.project.mycomerce.dto.user.CreateUserDto;
import com.project.mycomerce.dto.user.UpdateUserDto;
import com.project.mycomerce.mapper.AutoMapper;
import com.project.mycomerce.repository.security.UserRepository;
import com.project.mycomerce.service.security.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public User save(CreateUserDto createUser) {
        User user = AutoMapper.copyProperties(createUser,User.class);
        var UserFromDb = userRepository.save(user);
        return  UserFromDb;
    }

    @Override
    public User update(UpdateUserDto updateUser) {
        User UserFromDb = userRepository.findById(updateUser.getId()).get();
        BeanUtils.copyProperties(updateUser, UserFromDb);
        userRepository.save(UserFromDb);
        return UserFromDb;
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
