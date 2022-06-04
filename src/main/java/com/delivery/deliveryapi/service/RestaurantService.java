package com.delivery.deliveryapi.service;

import com.delivery.deliveryapi.Utils.CheckPrice;
import com.delivery.deliveryapi.dto.RestaurantDto;
import com.delivery.deliveryapi.model.Restaurant;
import com.delivery.deliveryapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final CheckPrice checkPrice;
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,CheckPrice checkPrice) {
        this.restaurantRepository = restaurantRepository;
        this.checkPrice = checkPrice;
    }

    public RestaurantDto registerRestaurant(RestaurantDto restaurantRequestDto) throws IllegalArgumentException {
        checkPrice.checkPrice(restaurantRequestDto.getMinOrderPrice(), 100,1000,100000);
        checkPrice.checkPrice(restaurantRequestDto.getDeliveryFee(), 500,0,10000);
        Restaurant restaurant = new Restaurant(restaurantRequestDto);
        return new RestaurantDto(restaurantRepository.save(restaurant));
    }

    public List<RestaurantDto> readRestaurants() {
        return restaurantRepository.findAll().stream().map(RestaurantDto::new).collect(Collectors.toList());
    }
}
