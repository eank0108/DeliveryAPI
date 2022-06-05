package com.delivery.deliveryapi.repository;

import com.delivery.deliveryapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUsername(String username);
}