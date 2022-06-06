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
@Table(name = "orders")
public class Order extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private String deliveryFee;

    @Column(nullable = false)
    private Long totalPrice;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private List<OrderFood> orderFoods;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User customer;


    public Order(Restaurant restaurant, Long totalPrice, List<OrderFood> orderFoods) {
        this.restaurantName = restaurant.getName();
        this.deliveryFee = restaurant.getDeliveryFee();
        this.totalPrice = totalPrice;
        this.orderFoods = orderFoods;
        // this.customer = customer;
    }
}
