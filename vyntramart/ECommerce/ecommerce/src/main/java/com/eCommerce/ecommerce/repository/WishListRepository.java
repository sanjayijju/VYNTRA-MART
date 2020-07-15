package com.eCommerce.ecommerce.repository;

import com.eCommerce.ecommerce.model.User;
import com.eCommerce.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    WishList findByUser(User user);
}
