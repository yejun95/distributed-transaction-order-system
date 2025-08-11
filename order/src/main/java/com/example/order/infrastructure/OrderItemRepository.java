package com.example.order.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<com.example.order.domain.OrderItem, Long> {
    List<com.example.order.domain.OrderItem> findAllByOrderId(Long orderId);

}
