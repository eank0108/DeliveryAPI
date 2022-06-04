package com.delivery.deliveryapi.controller;

import com.delivery.deliveryapi.dto.OrderFoodResponseDto;
import com.delivery.deliveryapi.dto.OrderRequestDto;
import com.delivery.deliveryapi.dto.OrderResponseDto;
import com.delivery.deliveryapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/request")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> readOrders() {
        return orderService.readOrders();
    }
}
