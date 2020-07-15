package com.eCommerce.ecommerce.service;

import com.eCommerce.ecommerce.model.BillingAddress;
import com.eCommerce.ecommerce.model.Cart;
import com.eCommerce.ecommerce.model.User;
import com.eCommerce.ecommerce.model.WishList;

import java.util.Optional;

public interface OrdersService {

    // BILLING ADDRESS SERVICES
    Optional<BillingAddress> getByBillingAddressId(Long billingAddressId);


    // CART SERVICES
    Cart getCartByUser(User user);

    void saveCart(Cart cart);

    void deleteCart(Cart cart);

    // WISHLIST SERVICES
    WishList getWishlistByUser(User user);

    Optional<WishList> getByWishListId(Long wishListId);

    void saveWishList(WishList wishList);

    void deleteWishList(WishList wishList);
}
