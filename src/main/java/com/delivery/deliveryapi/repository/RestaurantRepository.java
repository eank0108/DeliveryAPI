package com.delivery.deliveryapi.repository;

import com.delivery.deliveryapi.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
