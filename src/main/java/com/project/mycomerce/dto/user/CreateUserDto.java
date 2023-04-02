package com.project.mycomerce.dto.user;

import com.project.mycomerce.annotation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CreateUserDto {

    @NotBlank(message = "el usuario no puede estar vacio")
    @Size(min = 8, message = "el nombre de usuario debe tener al menos 8 digitos")
    private String username;

    @NotBlank(message = "el correo electronico no puede estar vacio")
    @Email(message = "el correo eletronico no es valido")
    private String email;

    @ValidPassword()
    private String password;

}
