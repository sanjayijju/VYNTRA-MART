package com.eCommerce.ecommerce.service;

import com.eCommerce.ecommerce.model.Product;
import com.eCommerce.ecommerce.model.ProductBrand;
import com.eCommerce.ecommerce.model.ProductType;
import com.eCommerce.ecommerce.repository.ProductBrandRepository;
import com.eCommerce.ecommerce.repository.ProductRepository;
import com.eCommerce.ecommerce.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductBrandRepository productBrandRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<Product> findByProductRatingGreaterThanEqual(Long rating) {
        return productRepository.findByRatingGreaterThanEqualOrderByRating(rating);
    }

    @Override
    public List<Product> findByProductBrandId(Long productBrandId) {
        Optional<ProductBrand> temp = getProductBrand(productBrandId);
        return productRepository.findByProductBrand(temp);
    }

    @Override
    public List<Product> findByProductTypeId(Long productTypeId) {
        Optional<ProductType> temp = getProductType(productTypeId);
        return productRepository.findByProductType(temp);
    }

    @Override
    public Optional<Product> findByProductId(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Optional<ProductBrand> getProductBrand(Long brandId) {
        return productBrandRepository.findById(brandId);
    }

    @Override
    public ProductBrand getProductBrand(String brandName) {
        return productBrandRepository.findByBrandName(brandName);
    }

    @Override
    public void saveBrand(ProductBrand productBrand) {
        productBrandRepository.save(productBrand);
    }

    @Override
    public void deleteBrand(ProductBrand productBrand) {
        productBrandRepository.delete(productBrand);
    }

    @Override
    public List<ProductBrand> getAllBrands() {
        return productBrandRepository.findAll();
    }

    @Override
    public Optional<ProductType> getProductType(Long typeId) {
        return productTypeRepository.findById(typeId);
    }

    @Override
    public ProductType getProductType(String typeName) {
        return productTypeRepository.findByTypeName(typeName);
    }

    @Override
    public void saveType(ProductType productType) {
        productTypeRepository.save(productType);
    }

    @Override
    public void deleteType(ProductType productType) {
        productTypeRepository.delete(productType);
    }

    @Override
    public List<ProductType> getAllTypes() {
        return productTypeRepository.findAll();
    }
}
