package com.delivery.deliveryapi.dto;

import com.delivery.deliveryapi.model.Food;
import com.delivery.deliveryapi.model.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodDto {
    private Long id;

    private String name;

    private String price;
    private Restaurant restaurant;

    public FoodDto(Food food) {
        id = food.getId();
        name = food.getName();
        price = food.getPrice();
    }
}
