package com.delivery.deliveryapi.model;

import com.delivery.deliveryapi.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String minOrderPrice;

    @Column(nullable = false)
    private String deliveryFee;

    @OneToMany
    @JoinColumn(name = "restaurant_id")
    private List<Orders> orders;

    @OneToMany(mappedBy = "restaurant")
    private List<Food> foods;


    public Restaurant(RestaurantDto restaurantRequestDto) {
        this.name = restaurantRequestDto.getName();
        this.minOrderPrice = restaurantRequestDto.getMinOrderPrice();
        this.deliveryFee = restaurantRequestDto.getDeliveryFee();
    }
}
