package com.eCommerce.ecommerce.repository;

import com.eCommerce.ecommerce.model.Product;
import com.eCommerce.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUserName(String userName);
    User findByContactNumber(String contactNumber);
    User findByEmailAndPassword(String email, String password);
}