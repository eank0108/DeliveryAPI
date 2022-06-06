package com.delivery.deliveryapi.dto;

import com.delivery.deliveryapi.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class OrderResponseDto {
    private String restaurantName;

    private String deliveryFee;

    private Long totalPrice;

    private List<OrderFoodResponseDto> foods;

    public OrderResponseDto(Order order) {
        this.restaurantName = order.getRestaurantName();
        this.deliveryFee = order.getDeliveryFee();
        this.totalPrice = order.getTotalPrice();
        this.foods = order.getOrderFoods().stream().map(OrderFoodResponseDto::new).collect(Collectors.toList());

    }
}
