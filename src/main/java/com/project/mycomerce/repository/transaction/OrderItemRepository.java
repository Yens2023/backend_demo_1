package com.project.mycomerce.repository.transaction;

import com.project.mycomerce.domain.transaction.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
