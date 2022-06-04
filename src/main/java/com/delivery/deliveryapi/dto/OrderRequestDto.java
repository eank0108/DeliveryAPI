package com.delivery.deliveryapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter@Setter
public class OrderRequestDto {
    Long restaurantId;
    List<OrderFoodRequestDto> foods;
}
