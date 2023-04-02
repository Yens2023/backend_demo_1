package com.project.mycomerce.service.master.Impl;

import com.project.mycomerce.domain.master.Product;
import com.project.mycomerce.domain.master.ProductImages;
import com.project.mycomerce.dto.productimage.CreateProductImageDto;
import com.project.mycomerce.dto.productimage.ReadProductImageDto;
import com.project.mycomerce.dto.productimage.UpdateProductImageDto;
import com.project.mycomerce.exception.global.ResourceNotFoundException;
import com.project.mycomerce.mapper.AutoMapper;
import com.project.mycomerce.repository.master.ProductImagesRepository;
import com.project.mycomerce.repository.master.ProductRepository;
import com.project.mycomerce.service.master.IProductImagesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImagesService implements IProductImagesService {
    @Autowired
    private ProductImagesRepository productImagesRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public ReadProductImageDto create(CreateProductImageDto request) {

        if(!productRepository.existsById(request.getProduct_id())){
            throw new ResourceNotFoundException("El id de producto no es válido");
        }
        Product productFromDb = new Product();
        productFromDb.setId(request.getProduct_id());

        ProductImages productImages = AutoMapper.copyProperties(request, ProductImages.class);
        productImages.setProduct(productFromDb);
        productImagesRepository.save(productImages);
        ReadProductImageDto response = AutoMapper.copyProperties(productImages, ReadProductImageDto.class);


        return response;
    }

    @Override
    public ReadProductImageDto update(UpdateProductImageDto request) {
        ProductImages productImagesFromDb = productImagesRepository.findById(request.getId())
                .orElseThrow(()-> new ResourceNotFoundException("El id de producto imagen no es válido"));

        BeanUtils.copyProperties(request, productImagesFromDb);
        productImagesRepository.save(productImagesFromDb);
        ReadProductImageDto response = AutoMapper.copyProperties(productImagesFromDb, ReadProductImageDto.class);
        return response;
    }

    @Override
    public void deleteById(Long id) {
        if(!productImagesRepository.existsById(id)){
            throw new ResourceNotFoundException("El id de producto imagen no es válido");
        }
        productImagesRepository.deleteById(id);
    }

    @Override
    public ReadProductImageDto findById(Long id) {
        ProductImages productImagesFromDb = productImagesRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El id de producto imagen no es válido"));

        ReadProductImageDto response = AutoMapper.copyProperties(productImagesFromDb, ReadProductImageDto.class);

        return  response;
    }

    @Override
    public List<ReadProductImageDto> findByProductId(Long id) {
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("El id de producto no es válido");
        }
        List<ProductImages> images = productImagesRepository.findByProduct_Id(id);
        List<ReadProductImageDto> response = AutoMapper.copyListProperties(images, ReadProductImageDto.class);
        return response;
    }
}
