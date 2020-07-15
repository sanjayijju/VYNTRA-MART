package com.eCommerce.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @Column(name = "ProductId")
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    private Long productId;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "Stock")
    private Long stock;

    @Column(name = "Rating")
    private Long rating;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Discount")
    private Double discount;

    @Column(name = "Image")
    private String image;

    @Column(name = "Description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Type_id", nullable = false)
    @JsonBackReference
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Brand_id", nullable = false)
    @JsonBackReference
    private ProductBrand productBrand;

    @ManyToMany(mappedBy = "productList")
    private List<Cart> cartList;

    @ManyToMany(mappedBy = "productList")
    private List<WishList> wishList;

    public Product(){
    }

    public Product(String productName, Long stock, Long rating, Double price, Double discount, String image, String description, ProductType productType, ProductBrand productBrand) {
        this.productName = productName;
        this.stock = stock;
        this.rating = rating;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.description = description;
        this.productType = productType;
        this.productBrand = productBrand;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductBrand getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(ProductBrand productBrand) {
        this.productBrand = productBrand;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }
}