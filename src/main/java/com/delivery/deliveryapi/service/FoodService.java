package com.delivery.deliveryapi.service;

import com.delivery.deliveryapi.Utils.CheckPrice;
import com.delivery.deliveryapi.dto.FoodDto;
import com.delivery.deliveryapi.model.Food;
import com.delivery.deliveryapi.model.Restaurant;
import com.delivery.deliveryapi.repository.FoodRepository;
import com.delivery.deliveryapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final CheckPrice checkPrice;

    @Autowired
    public FoodService(FoodRepository foodRepository, CheckPrice checkPrice, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.checkPrice = checkPrice;
        this.restaurantRepository = restaurantRepository;
    }

    public void registerFoods(Long restaurantId, List<FoodDto> requestFoods) {
        List<Food> foods = new ArrayList<>();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 식당은 존재하지 않습니다."));
        for (FoodDto foodDto :
                requestFoods) {

            if (foods.stream().anyMatch(food -> foodDto.getName().equals(food.getName()))) {
                throw new IllegalArgumentException("이름이 같은 메뉴가 있습니다");
            }
            if(foodRepository.existsByRestaurantAndName(restaurant, foodDto.getName())){
                throw new IllegalArgumentException("이미 등록된 같은 메뉴가 있습니다");
            }
            checkPrice.checkPrice(foodDto.getPrice(), 100, 100, 1_000_000);
            foodDto.setRestaurant(restaurant);
            foods.add(new Food(foodDto));
        }
        List<FoodDto> responseFoods = foodRepository.saveAll(foods).stream().map(FoodDto::new).collect(Collectors.toList());
        // return responseFoods;
    }

    public List<FoodDto> getFood(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId).stream().map(FoodDto::new).collect(Collectors.toList());
    }
}
