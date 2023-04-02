package com.project.mycomerce.service.security.Impl;

import com.project.mycomerce.config.jwt.JwtTokenUtil;
import com.project.mycomerce.domain.security.Role;
import com.project.mycomerce.domain.security.User;
import com.project.mycomerce.dto.auth.LoginRequestDto;
import com.project.mycomerce.dto.auth.LoginResponseDto;
import com.project.mycomerce.dto.user.CreateUserDto;
import com.project.mycomerce.dto.user.ReadUserDto;
import com.project.mycomerce.exception.auth.EmailAlreadyExistsException;
import com.project.mycomerce.exception.auth.UsernameAlreadyExistsException;
import com.project.mycomerce.mapper.AutoMapper;
import com.project.mycomerce.repository.security.UserRepository;
import com.project.mycomerce.service.security.AuthService;
import com.project.mycomerce.service.security.RoleService;
import com.project.mycomerce.utils.SD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + username)
                );

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>()
        );
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);
        return new LoginResponseDto(jwt);
    }

    @Override
    public ReadUserDto register(CreateUserDto request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException("El nombre de usuario ya está en uso!");
        }

        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("El correo electrónico ya está en uso!");
        }

        request.setPassword(encoder.encode(request.getPassword()));

        User user = AutoMapper.copyProperties(request,User.class);
        // agregando rol user
        Role role = roleService.findByName(SD.USER_ROL);
        Set<Role> rolList = new HashSet<>();
        rolList.add(role);
        user.setRoles(rolList);

        userRepository.save(user);


        return AutoMapper.copyProperties(user,ReadUserDto.class);
    }
}
