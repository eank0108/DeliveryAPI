package com.delivery.deliveryapi.model;

import com.delivery.deliveryapi.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Food {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    public Food(FoodDto foodDto) {

        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
        this.restaurant = foodDto.getRestaurant();
    }

}
