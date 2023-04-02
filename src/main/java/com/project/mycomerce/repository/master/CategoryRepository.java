package com.project.mycomerce.repository.master;

import com.project.mycomerce.domain.master.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByName(String username);
    Boolean existsByNameAndIdNot(String username, Long id);
}
