package com.example.product.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "product_reservations")
public class ProductReservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestId;

    private Long productId;

    private Long reservedQuantity;

    private Long reservedPrice;

    @Enumerated(EnumType.STRING)
    private ProductReservationStatus status;

    public ProductReservation() {
    }

    public ProductReservation(String requestId, Long productId, Long reservedQuantity, Long reservedPrice) {
        this.requestId = requestId;
        this.productId = productId;
        this.reservedQuantity = reservedQuantity;
        this.reservedPrice = reservedPrice;
        status = ProductReservationStatus.RESERVED;
    }

    public Long getReservedPrice() {
        return reservedPrice;
    }

    public enum ProductReservationStatus {
        RESERVED,
        CONFIRMED,
        CANCELLED
    }
}
