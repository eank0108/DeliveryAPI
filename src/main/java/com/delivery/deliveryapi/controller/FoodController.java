package com.delivery.deliveryapi.controller;

import com.delivery.deliveryapi.dto.FoodDto;
import com.delivery.deliveryapi.model.Food;
import com.delivery.deliveryapi.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Secured("ROLE_CEO")
    @PostMapping("/restaurant/{restaurantId}/food/register")
    @ResponseStatus(value = HttpStatus.OK)
    public void registerFoods(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foods) {
        foodService.registerFoods(restaurantId, foods);
        // return foodService.registerFoods(restaurantId, foods);
    }
    @Secured("ROLE_USER")
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodDto> getFood(@PathVariable Long restaurantId) {
        return foodService.getFood(restaurantId);
    }


}
