package com.project.mycomerce.repository.master;

import com.project.mycomerce.domain.master.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Boolean existsByName(String username);
    Boolean existsByNameAndIdNot(String username, Long id);
}
