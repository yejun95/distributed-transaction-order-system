package com.example.product.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    private Long price;

    private Long reservedQuantity;

    @Version
    private Long version;

    public Product() {
    }

    public Product(Long quantity, Long price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Long reserve(Long requestedQuantity) {
        long reservableQuantity = this.quantity - this.reservedQuantity;

        if (reservableQuantity < requestedQuantity) {
            throw new RuntimeException("예약할 수 있는 수량이 부족합니다.");
        }

        reservableQuantity += requestedQuantity;

        return price * requestedQuantity;
    }

    public Long calculatePrice(Long quantity) {
        return price * quantity;
    }

    public void buy(Long quantity) {
        if (this.quantity < quantity) {
            throw new RuntimeException("재고가 부족합니다.");
        }

        this.quantity = this.quantity - quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", reservedQuantity=" + reservedQuantity +
                ", version=" + version +
                '}';
    }
}
