package com.delivery.deliveryapi.dto;

import com.delivery.deliveryapi.model.OrderFood;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class OrderFoodResponseDto {
    String name;
    Long quantity;
    Long price;

    public OrderFoodResponseDto(OrderFood orderFood) {
        name = orderFood.getFood().getName();
        quantity = orderFood.getQuantity();
        price = orderFood.getPrice();
    }

}
