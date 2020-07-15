package com.eCommerce.ecommerce.service;

import com.eCommerce.ecommerce.model.BillingAddress;
import com.eCommerce.ecommerce.model.Cart;
import com.eCommerce.ecommerce.model.User;
import com.eCommerce.ecommerce.model.WishList;
import com.eCommerce.ecommerce.repository.BillingAddressRepository;
import com.eCommerce.ecommerce.repository.CartRepository;
import com.eCommerce.ecommerce.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BillingAddressRepository billingAddressRepository;

    @Override
    public Optional<BillingAddress> getByBillingAddressId(Long billingAddressId) {
        return billingAddressRepository.findById(billingAddressId);
    }

    @Override
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public void saveWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

    @Override
    public void deleteWishList(WishList wishList) {
        wishListRepository.delete(wishList);
    }

    @Override
    public WishList getWishlistByUser(User user) {
        return wishListRepository.findByUser(user);
    }

    @Override
    public Optional<WishList> getByWishListId(Long wishListId) {
        return wishListRepository.findById(wishListId);
    }
}
