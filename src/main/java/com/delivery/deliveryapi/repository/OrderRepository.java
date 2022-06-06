package com.delivery.deliveryapi.repository;

import com.delivery.deliveryapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
