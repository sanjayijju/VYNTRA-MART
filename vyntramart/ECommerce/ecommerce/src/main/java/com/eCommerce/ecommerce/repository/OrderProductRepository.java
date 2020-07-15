package com.eCommerce.ecommerce.repository;

import com.eCommerce.ecommerce.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
