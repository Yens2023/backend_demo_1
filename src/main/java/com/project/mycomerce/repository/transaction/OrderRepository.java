package com.project.mycomerce.repository.transaction;

import com.project.mycomerce.domain.transaction.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
