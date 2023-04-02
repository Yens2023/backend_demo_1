package com.project.mycomerce.controller.v1;

import com.project.mycomerce.dto.auth.LoginRequestDto;
import com.project.mycomerce.dto.auth.LoginResponseDto;
import com.project.mycomerce.dto.custom.APIResponse;
import com.project.mycomerce.dto.user.CreateUserDto;
import com.project.mycomerce.dto.user.ReadUserDto;
import com.project.mycomerce.service.security.AuthService;
import com.project.mycomerce.utils.SD;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private  final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<APIResponse> login (@RequestBody @Valid LoginRequestDto loginRequest){
        var userDto = authService.login(loginRequest);

        APIResponse responseDto = APIResponse
                .<LoginResponseDto>builder()
                .status(SD.SUCCESS)
                .results(userDto)
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@RequestBody @Valid CreateUserDto request){
        var userDto = authService.register(request);

        APIResponse<ReadUserDto> responseDto  = APIResponse
                .<ReadUserDto>builder()
                .status(SD.SUCCESS)
                .results(userDto)
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

}
