package com.project.mycomerce.service.security;

import com.project.mycomerce.dto.auth.LoginRequestDto;
import com.project.mycomerce.dto.auth.LoginResponseDto;
import com.project.mycomerce.dto.user.CreateUserDto;
import com.project.mycomerce.dto.user.ReadUserDto;

public interface AuthService {
    LoginResponseDto login (LoginRequestDto request);
    ReadUserDto register (CreateUserDto request);

}
