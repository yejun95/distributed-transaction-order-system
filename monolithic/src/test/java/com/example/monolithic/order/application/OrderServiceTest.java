package com.example.monolithic.order.application;

import com.example.monolithic.order.application.dto.PlaceOrderCommand;
import com.example.monolithic.order.domain.Order;
import com.example.monolithic.order.domain.OrderItem;
import com.example.monolithic.order.infrastructure.OrderItemRepository;
import com.example.monolithic.order.infrastructure.OrderRepository;
import com.example.monolithic.point.application.PointService;
import com.example.monolithic.point.domain.Point;
import com.example.monolithic.point.infrastructure.PointRepository;
import com.example.monolithic.product.application.ProductService;
import com.example.monolithic.product.domain.Product;
import com.example.monolithic.product.infrastructure.ProductRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PointService pointService;

    @Autowired
    ProductService productService;


    @BeforeEach
    public void BeforeEach() {
        pointRepository.save(new Point(1L, 10000L));

        Product product1 = new Product(100L, 100L);
        Product product2 = new Product(100L, 200L);

        productRepository.save(product1);
        productRepository.save(product2);
    }

    @AfterEach
    public void AfterEach() {
        pointRepository.deleteById(1L);

        productRepository.deleteById(1L);
        productRepository.deleteById(2L);
    }

    @Test
    @Transactional
    public void 물건구매와_포인트차감이_일어난다() {
        Order order = orderRepository.save(new Order());
        Long totalPrice = 0L;

        List<PlaceOrderCommand.OrderItem> orderItems = List.of(
                new PlaceOrderCommand.OrderItem(1L, 1L),
                new PlaceOrderCommand.OrderItem(2L, 2L)
        );

        for (PlaceOrderCommand.OrderItem item : orderItems) {
            OrderItem orderItem = new OrderItem(order.getId(), item.productId(), item.quantity());
            orderItemRepository.save(orderItem);

            Long price = productService.buy(item.productId(), item.quantity());
            totalPrice += price;
        }

        pointService.use(1L, totalPrice);

        Optional<Point> point1 = pointRepository.findById(1L);
        assertThat(point1.get().getAmount()).isEqualTo(9500);
    }

}