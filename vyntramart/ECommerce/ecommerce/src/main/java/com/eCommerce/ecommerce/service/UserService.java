package com.eCommerce.ecommerce.service;

import com.eCommerce.ecommerce.model.User;

import java.util.List;

public interface UserService {

    User findByUserEmail(String email);
    User findByUserUserName(String userName);
    User findByUserContactNumber(String contactNumber);
    User findByUserEmailAndPassword(String email, String password);
    void saveUser(User user);
    void deleteUser(User user);

    List<User> getAllUsers();
}
