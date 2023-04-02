package com.project.mycomerce.repository.master;

import com.project.mycomerce.domain.master.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {
    List<ProductImages> findByProduct_Id(Long productId);
}
