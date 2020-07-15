package com.eCommerce.ecommerce.repository;

import com.eCommerce.ecommerce.model.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
