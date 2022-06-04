package com.delivery.deliveryapi.repository;

import com.delivery.deliveryapi.dto.FoodDto;
import com.delivery.deliveryapi.model.Food;
import com.delivery.deliveryapi.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurantId(Long restaurantId);

    Optional<Food> findByIdAndRestaurant(Long id, Restaurant restaurant);


    boolean existsByRestaurantAndName(Restaurant restaurant, String name);
}
