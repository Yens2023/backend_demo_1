package com.project.mycomerce.service.master.Impl;

import com.project.mycomerce.domain.master.Brand;
import com.project.mycomerce.domain.master.Category;
import com.project.mycomerce.domain.master.Product;
import com.project.mycomerce.dto.product.CreateProductDto;
import com.project.mycomerce.dto.product.ReadProductDto;
import com.project.mycomerce.dto.product.UpdateProductDto;
import com.project.mycomerce.exception.global.ResourceNotFoundException;
import com.project.mycomerce.mapper.AutoMapper;
import com.project.mycomerce.repository.master.BrandRepository;
import com.project.mycomerce.repository.master.CategoryRepository;
import com.project.mycomerce.repository.master.ProductRepository;
import com.project.mycomerce.service.master.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public ReadProductDto create(CreateProductDto request) {
        Brand brandFromDb = brandRepository.findById(request.getBrand_id())
                .orElseThrow(()-> new ResourceNotFoundException("El id proporcionado de la marca no es válida"));


        Category categoryFromDb = categoryRepository.findById(request.getCategory_id())
                .orElseThrow(()-> new ResourceNotFoundException("El id proporcionado de la categoria no es válida"));

        Product product = AutoMapper.copyProperties(request, Product.class);
        product.setCategory(categoryFromDb);
        product.setBrand(brandFromDb);

        ReadProductDto productDto = AutoMapper.copyProperties(product, ReadProductDto.class);
        productDto.setCategory(categoryFromDb.getName());
        productDto.setBrand(brandFromDb.getName());

        return productDto;
    }

    @Override
    public ReadProductDto update(UpdateProductDto request) {
        Brand brandFromDb = brandRepository.findById(request.getBrand_id())
                .orElseThrow(()-> new ResourceNotFoundException("El id proporcionado de la marca no es válida"));


        Category categoryFromDb = categoryRepository.findById(request.getCategory_id())
                .orElseThrow(()-> new ResourceNotFoundException("El id proporcionado de la categoria no es válida"));

        Product product = AutoMapper.copyProperties(request, Product.class);
        product.setCategory(categoryFromDb);
        product.setBrand(brandFromDb);

        ReadProductDto productDto = AutoMapper.copyProperties(product, ReadProductDto.class);
        productDto.setCategory(categoryFromDb.getName());
        productDto.setBrand(brandFromDb.getName());

        return productDto;
    }

    @Override
    public void deletById(Long id) {
        Product productFromDb = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El producto no existe"));
        productRepository.deleteById(id);
    }

    @Override
    public ReadProductDto findById(Long id) {
        Product productFromDb = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El producto no existe"));

        ReadProductDto productDto = AutoMapper.copyProperties(productFromDb, ReadProductDto.class);
        productDto.setCategory(productFromDb.getCategory().getName());
        productDto.setBrand(productFromDb.getBrand().getName());

        return null;
    }

    @Override
    public List<ReadProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        List<ReadProductDto> productDtoList = new ArrayList<>();

        for(Product product: products){
            var v = AutoMapper.copyProperties(product,ReadProductDto.class);
            v.setBrand(product.getBrand().getName());
            v.setCategory(product.getCategory().getName());
            productDtoList.add(v);
        }

        return productDtoList;
    }
}
