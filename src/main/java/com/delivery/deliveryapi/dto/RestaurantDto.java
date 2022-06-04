package com.delivery.deliveryapi.dto;

import com.delivery.deliveryapi.model.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantDto {
    private Long id;

    private String name;

    private String minOrderPrice;

    private String deliveryFee;

    public RestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.minOrderPrice = restaurant.getMinOrderPrice();
        this.deliveryFee = restaurant.getDeliveryFee();
    }
}
