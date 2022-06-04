package com.delivery.deliveryapi.dto;

import com.delivery.deliveryapi.model.OrderFood;
import com.delivery.deliveryapi.model.Orders;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

public class OrderResponseDto {
    private String restaurantName;

    private String deliveryFee;

    private Long totalPrice;

    private List<OrderFoodResponseDto> foods;

    public OrderResponseDto(Orders order) {
        this.restaurantName = order.getRestaurantName();
        this.deliveryFee = order.getDeliveryFee();
        this.totalPrice = order.getTotalPrice();
        this.foods = order.getFoods().stream().map(OrderFoodResponseDto::new).collect(Collectors.toList());

    }
}
