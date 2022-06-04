package com.delivery.deliveryapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private String deliveryFee;

    @Column(nullable = false)
    private Long totalPrice;

    @OneToMany
    @JoinColumn(name = "orders_id")
    private List<OrderFood> foods;


    public Orders(Restaurant restaurant, Long totalPrice, List<OrderFood> orderFoods) {
        this.restaurantName = restaurant.getName();
        this.deliveryFee = restaurant.getDeliveryFee();
        this.totalPrice = totalPrice;
        this.foods = orderFoods;
    }
}
