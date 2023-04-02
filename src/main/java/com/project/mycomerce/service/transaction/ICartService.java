package com.project.mycomerce.service.transaction;

import com.project.mycomerce.dto.cart.CreateCartDto;
import com.project.mycomerce.dto.cart.ReadCartDto;
import com.project.mycomerce.dto.cart.UpdateCartDto;

import java.util.List;

public interface ICartService {
    ReadCartDto create(CreateCartDto request);
    ReadCartDto update(UpdateCartDto request);
    void deleteById(Long id);
    List<ReadCartDto> getAllByUserId(Long id);
}
