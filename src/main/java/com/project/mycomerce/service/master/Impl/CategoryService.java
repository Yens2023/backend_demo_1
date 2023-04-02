package com.project.mycomerce.service.master.Impl;

import com.project.mycomerce.domain.master.Category;
import com.project.mycomerce.dto.category.CreateCategoryDto;
import com.project.mycomerce.dto.category.ReadCategoryDto;
import com.project.mycomerce.dto.category.UpdateCategoryDto;
import com.project.mycomerce.exception.global.AlreadyExistsException;
import com.project.mycomerce.exception.global.ResourceNotFoundException;
import com.project.mycomerce.mapper.AutoMapper;
import com.project.mycomerce.repository.master.CategoryRepository;
import com.project.mycomerce.service.master.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ReadCategoryDto create(CreateCategoryDto request) {
        if(categoryRepository.existsByName(request.getName())){
            throw new AlreadyExistsException("La categoria ingresada ya existe");
        }
        Category category = AutoMapper.copyProperties(request, Category.class);
        categoryRepository.save(category);
        ReadCategoryDto categoryDto = AutoMapper.copyProperties(category,ReadCategoryDto.class);

        return categoryDto;
    }

    @Override
    public ReadCategoryDto update(UpdateCategoryDto request) {
        Category categoryFromDb = categoryRepository.findById(request.getId())
                .orElseThrow(()-> new ResourceNotFoundException("La categoria no esta disponible"));

        if(categoryRepository.existsByNameAndIdNot(request.getName(), request.getId())){
            throw new AlreadyExistsException("La categoria ingresada ya existe");
        }
        categoryFromDb.setName(request.getName());
        categoryRepository.save(categoryFromDb);

        ReadCategoryDto categoryDto = AutoMapper.copyProperties(categoryFromDb, ReadCategoryDto.class);

        return categoryDto;
    }

    @Override
    public void deleteById(Long id) {
        Category categoryFromDb = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("La categoria no esta disponible"));

        categoryRepository.deleteById(id);
    }

    @Override
    public ReadCategoryDto findById(Long id) {
        Category categoryFromDb = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("La categoria no esta disponible"));
        ReadCategoryDto categoryDto = AutoMapper.copyProperties(categoryFromDb,ReadCategoryDto.class);

        return categoryDto;
    }

    @Override
    public List<ReadCategoryDto> getAll() {
        List<Category> categoriesFromDb = categoryRepository.findAll();
        List<ReadCategoryDto> categories = AutoMapper.copyListProperties(categoriesFromDb, ReadCategoryDto.class);
        return categories;
    }
}
