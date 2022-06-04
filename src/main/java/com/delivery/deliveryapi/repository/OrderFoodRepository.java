package com.delivery.deliveryapi.repository;

import com.delivery.deliveryapi.model.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {

}
