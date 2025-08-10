package com.example.monolithic.order.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
        status = OrderStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void complete() {
        status = OrderStatus.COMPLETED;
    }

    public enum OrderStatus {
        CREATED, COMPLETED
    }

}
