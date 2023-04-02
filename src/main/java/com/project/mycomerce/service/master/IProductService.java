package com.project.mycomerce.service.master;

import com.project.mycomerce.dto.product.CreateProductDto;
import com.project.mycomerce.dto.product.ReadProductDto;
import com.project.mycomerce.dto.product.UpdateProductDto;

import java.util.List;

public interface IProductService {
    ReadProductDto create(CreateProductDto request);
    ReadProductDto update(UpdateProductDto request);
    void deletById(Long id);
    ReadProductDto findById(Long id);
    List<ReadProductDto> getAll();
}
