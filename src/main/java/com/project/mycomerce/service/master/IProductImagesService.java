package com.project.mycomerce.service.master;

import com.project.mycomerce.dto.productimage.CreateProductImageDto;
import com.project.mycomerce.dto.productimage.ReadProductImageDto;
import com.project.mycomerce.dto.productimage.UpdateProductImageDto;

import java.util.List;

public interface IProductImagesService {
    ReadProductImageDto create (CreateProductImageDto request);
    ReadProductImageDto update (UpdateProductImageDto request);
    void deleteById(Long id);
    ReadProductImageDto findById(Long id);
    List<ReadProductImageDto> findByProductId(Long id);
}
