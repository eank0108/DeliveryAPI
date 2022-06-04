package com.delivery.deliveryapi.controller;

import com.delivery.deliveryapi.dto.RestaurantDto;
import com.delivery.deliveryapi.model.Restaurant;
import com.delivery.deliveryapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant/register")
    public RestaurantDto registerRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.registerRestaurant(restaurantDto);
    }
    @GetMapping("/restaurants")
    public List<RestaurantDto> readRestaurants() {
        return restaurantService.readRestaurants();
    }
}
