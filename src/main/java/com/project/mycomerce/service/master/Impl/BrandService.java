package com.project.mycomerce.service.master.Impl;

import com.project.mycomerce.domain.master.Brand;
import com.project.mycomerce.dto.brand.CreateBrandDto;
import com.project.mycomerce.dto.brand.ReadBrandDto;
import com.project.mycomerce.dto.brand.UpdateBrandDto;
import com.project.mycomerce.exception.global.AlreadyExistsException;
import com.project.mycomerce.exception.global.ResourceNotFoundException;
import com.project.mycomerce.mapper.AutoMapper;
import com.project.mycomerce.repository.master.BrandRepository;
import com.project.mycomerce.service.master.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public ReadBrandDto create(CreateBrandDto request) {
        if(brandRepository.existsByName(request.getName())){
            throw new AlreadyExistsException("La marca ingresada ya existe");
        }
        Brand brand = AutoMapper.copyProperties(request, Brand.class);
        brandRepository.save(brand);
        ReadBrandDto brandDto = AutoMapper.copyProperties(brand, ReadBrandDto.class);
        return brandDto;
    }

    @Override
    public ReadBrandDto update(UpdateBrandDto request) {
        Brand brandFromDb = brandRepository.findById(request.getId())
                .orElseThrow(()-> new ResourceNotFoundException("La marca no esta disponible")) ;

        if(brandRepository.existsByNameAndIdNot(request.getName(),request.getId())){
            throw new AlreadyExistsException("La marca ingresada ya existe");
        }

        brandFromDb.setName(request.getName());
        brandRepository.save(brandFromDb);

        ReadBrandDto brandDto = AutoMapper.copyProperties(brandFromDb, ReadBrandDto.class);

        return brandDto;
    }

    @Override
    public void deleteById(Long id) {
        Brand brandFromDb = brandRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("La marca no esta disponible"));
        brandRepository.deleteById(id);
    }

    @Override
    public ReadBrandDto findById(Long id) {
        Brand brandFromDb = brandRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("La marca no esta disponible")) ;

        ReadBrandDto brandDto = AutoMapper.copyProperties(brandFromDb, ReadBrandDto.class);

        return brandDto;
    }

    @Override
    public List<ReadBrandDto> getAll() {
        List<Brand> bransFromDb = brandRepository.findAll();
        List<ReadBrandDto> brands = AutoMapper.copyListProperties(bransFromDb, ReadBrandDto.class);
        return brands;
    }
}
