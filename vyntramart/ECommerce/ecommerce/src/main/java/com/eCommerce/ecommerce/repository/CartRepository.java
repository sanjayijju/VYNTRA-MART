package com.eCommerce.ecommerce.repository;

import com.eCommerce.ecommerce.model.Cart;
import com.eCommerce.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUser(User user);
}
