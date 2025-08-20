package com.example.product.application;

import com.example.product.application.dto.ProductReserveCommand;
import com.example.product.application.dto.ProductReserveResult;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

@Component
public class ProductFacadeService {

    private final ProductService productService;

    public ProductFacadeService(ProductService productService) {
        this.productService = productService;
    }

    public ProductReserveResult tryReserve(ProductReserveCommand command) {
        int tryCount = 0;

        while (tryCount < 3) {
            try {
                return productService.tryReserve(command);
            } catch (ObjectOptimisticLockingFailureException e) {
                tryCount++;
            }
        }
        throw new RuntimeException("예약에 실패했습니다.");
    }
}
