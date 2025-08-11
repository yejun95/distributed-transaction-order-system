package com.example.order.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<com.example.order.domain.Order, Long> {
}
