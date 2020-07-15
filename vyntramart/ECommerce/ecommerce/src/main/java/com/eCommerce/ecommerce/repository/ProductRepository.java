package com.eCommerce.ecommerce.repository;

import com.eCommerce.ecommerce.model.Product;
import com.eCommerce.ecommerce.model.ProductBrand;
import com.eCommerce.ecommerce.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByRatingGreaterThanEqualOrderByRating(Long rating);

    List<Product> findByProductBrand(Optional<ProductBrand> productBrand);

    List<Product> findByProductType(Optional<ProductType> productType);
}