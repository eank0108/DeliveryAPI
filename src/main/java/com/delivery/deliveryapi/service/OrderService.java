package com.delivery.deliveryapi.service;

import com.delivery.deliveryapi.dto.OrderFoodRequestDto;
import com.delivery.deliveryapi.dto.OrderFoodResponseDto;
import com.delivery.deliveryapi.dto.OrderRequestDto;
import com.delivery.deliveryapi.dto.OrderResponseDto;
import com.delivery.deliveryapi.model.Food;
import com.delivery.deliveryapi.model.OrderFood;
import com.delivery.deliveryapi.model.Orders;
import com.delivery.deliveryapi.model.Restaurant;
import com.delivery.deliveryapi.repository.FoodRepository;
import com.delivery.deliveryapi.repository.OrderFoodRepository;
import com.delivery.deliveryapi.repository.OrderRepository;
import com.delivery.deliveryapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderFoodRepository orderFoodRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository, FoodRepository foodRepository, OrderFoodRepository orderFoodRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.orderFoodRepository = orderFoodRepository;
    }

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식당 입니다."));

        Long totalPrice = 0L;
        Long totalQuantity = 0L;
        List<OrderFood> orderFoods = new ArrayList<>();

        for (OrderFoodRequestDto orderFood :
                orderRequestDto.getFoods()) {
            Long foodId = orderFood.getId();
            Food food = foodRepository.findByIdAndRestaurant(foodId,restaurant)
                    .orElseThrow(() -> new IllegalArgumentException("해당 메뉴는 존재하지 않는 메뉴입니다."));

            totalQuantity += orderFood.getQuantity();
            if (totalQuantity > 100) {
                throw new IllegalArgumentException("주문 수량은 100개를 초과할 수 없습니다.");
            }

            orderFoods.add(new OrderFood(food,orderFood.getQuantity()));
            totalPrice += Long.parseLong(food.getPrice()) * orderFood.getQuantity();
        }


        if (totalPrice < Long.parseLong(restaurant.getMinOrderPrice())) {
            throw new IllegalArgumentException("최소주문 가격 이상 주문해 주세요");
        }

        totalPrice += Long.parseLong(restaurant.getDeliveryFee());
        List<OrderFood> savedOrderFoods = orderFoodRepository.saveAll(orderFoods);

        Orders order = new Orders(restaurant, totalPrice, savedOrderFoods);
        return new OrderResponseDto(orderRepository.save(order));
    }

    public List<OrderResponseDto> readOrders() {
        return orderRepository.findAll().stream().map(OrderResponseDto::new).collect(Collectors.toList());
    }
}
