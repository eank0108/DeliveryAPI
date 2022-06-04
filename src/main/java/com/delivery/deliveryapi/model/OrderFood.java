package com.delivery.deliveryapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter@Setter
public class OrderFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "food_id")
    Food food;

    @Column(nullable = false)
    Long price;
    @Column(nullable = false)
    Long quantity;

    public OrderFood(Food food, Long quantity) {
        this.quantity = quantity;
        this.food = food;
        this.price = quantity * Long.parseLong(food.getPrice());
    }
}
