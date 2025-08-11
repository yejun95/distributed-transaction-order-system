package com.example.point.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "points")
public class Point {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long userId;

    private Long amount;

    public Point() {

    }

    public Point(Long userId, Long amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public void use(Long amount) {
        if (this.amount < amount) {
            throw new RuntimeException("잔액이 부족합니다.");
        }

        this.amount = this.amount - amount;
    }
}
