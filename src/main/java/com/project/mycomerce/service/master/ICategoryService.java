package com.project.mycomerce.service.master;

import com.project.mycomerce.dto.category.CreateCategoryDto;
import com.project.mycomerce.dto.category.ReadCategoryDto;
import com.project.mycomerce.dto.category.UpdateCategoryDto;

import java.util.List;

public interface ICategoryService {
    ReadCategoryDto create(CreateCategoryDto request);
    ReadCategoryDto update(UpdateCategoryDto request);
    void deleteById(Long id);
    ReadCategoryDto findById(Long id);
    List<ReadCategoryDto> getAll();
}
