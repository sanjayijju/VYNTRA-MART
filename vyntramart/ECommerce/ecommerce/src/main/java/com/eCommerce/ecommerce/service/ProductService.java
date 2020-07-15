package com.eCommerce.ecommerce.service;

import com.eCommerce.ecommerce.model.Product;
import com.eCommerce.ecommerce.model.ProductBrand;
import com.eCommerce.ecommerce.model.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    //PRODUCT
    List<Product> findByProductRatingGreaterThanEqual(Long rating);
    List<Product> findByProductBrandId(Long productBrandId);
    List<Product> findByProductTypeId(Long productTypeId);
    Optional<Product> findByProductId(Long productId);
    void saveProduct(Product product);
    void deleteProduct(Product product);

    //PRODUCT BRAND
    Optional<ProductBrand> getProductBrand(Long brandId);
    ProductBrand getProductBrand(String brandName);
    void saveBrand(ProductBrand productBrand);
    void deleteBrand(ProductBrand productBrand);
    List<ProductBrand> getAllBrands();


    //PRODUCT TYPE
    Optional<ProductType> getProductType(Long typeId);
    ProductType getProductType(String typeName);
    void saveType(ProductType productType);
    void deleteType(ProductType productType);
    List<ProductType> getAllTypes();
}
