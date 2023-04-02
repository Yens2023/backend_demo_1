package com.project.mycomerce.repository.transaction;

import com.project.mycomerce.domain.transaction.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser_IdAndProduct_Id(Long userId, Long productId);

    List<Cart> findByUser_Id(Long userId);
}
