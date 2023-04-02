package com.project.mycomerce.handler;

import com.project.mycomerce.dto.custom.APIResponse;
import com.project.mycomerce.dto.custom.ErrorDTO;
import com.project.mycomerce.exception.auth.UsernameAlreadyExistsException;
import com.project.mycomerce.exception.global.AlreadyExistsException;
import com.project.mycomerce.exception.global.ResourceNotFoundException;
import com.project.mycomerce.utils.SD;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIResponse<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus(SD.FAILED);
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> handleBadRequestException(MethodArgumentNotValidException exception) {
        APIResponse<?> serviceResponse = new APIResponse<>();
        List<ErrorDTO> errors = new ArrayList<>();

        var ErroresLista =  exception.getBindingResult().getFieldErrors();

        for (var error: ErroresLista) {
            errors.add(new ErrorDTO(
                    error.getField(),
                    error.getDefaultMessage()
            ));
        }

        serviceResponse.setStatus(SD.FAILED);
        serviceResponse.setErrors(errors);
        return serviceResponse;
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public APIResponse<?> handleAlreadyExists(AlreadyExistsException exception){
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus(SD.FAILED);
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("",exception.getMessage())));
        return serviceResponse;
    }


}
