package com.project.mycomerce.service.master;

import com.project.mycomerce.dto.brand.CreateBrandDto;
import com.project.mycomerce.dto.brand.ReadBrandDto;
import com.project.mycomerce.dto.brand.UpdateBrandDto;

import java.util.List;

public interface IBrandService {
    ReadBrandDto create(CreateBrandDto request);
    ReadBrandDto update(UpdateBrandDto request);
    void deleteById(Long id);
    ReadBrandDto findById(Long id);
    List<ReadBrandDto> getAll();
}
