package com.eCommerce.ecommerce.repository;

import com.eCommerce.ecommerce.model.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {
    ProductBrand findByBrandName(String brandName);
}