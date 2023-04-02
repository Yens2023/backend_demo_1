package com.project.mycomerce.controller.v1;

import com.project.mycomerce.domain.security.User;
import com.project.mycomerce.dto.custom.MessageResponse;
import com.project.mycomerce.dto.user.CreateUserDto;
import com.project.mycomerce.dto.user.ReadUserDto;
import com.project.mycomerce.dto.user.UpdateUserDto;
import com.project.mycomerce.mapper.AutoMapper;
import com.project.mycomerce.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping()
    public ResponseEntity<MessageResponse> Register(@RequestBody CreateUserDto createUser){

        if(userService.existsByUsername(createUser.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body( new MessageResponse("Error: Username is already taken!"));
        }

        if(userService.existsByEmail(createUser.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        userService.save(createUser);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));



    }

    @PutMapping()
    public ResponseEntity<MessageResponse> Update(@RequestBody UpdateUserDto updateUser){

        if(!userService.findById(updateUser.getId()).isPresent()){
            return ResponseEntity.notFound().build();
        }
        userService.update(updateUser);

        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>Delete(@PathVariable Long id){

        if(!userService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }

        userService.deleteById(id);
        return  ResponseEntity.noContent().build();

    }

    @GetMapping()
    public List<ReadUserDto> GetAll(){
        List<User> userList = userService.findAll();
        var resultado = AutoMapper.copyListProperties(userList, ReadUserDto.class);
        return resultado;

    }


}
