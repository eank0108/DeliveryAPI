package com.delivery.deliveryapi.repository;

import com.delivery.deliveryapi.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
