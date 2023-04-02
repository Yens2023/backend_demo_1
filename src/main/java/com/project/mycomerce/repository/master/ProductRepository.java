package com.project.mycomerce.repository.master;

import com.project.mycomerce.domain.master.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
