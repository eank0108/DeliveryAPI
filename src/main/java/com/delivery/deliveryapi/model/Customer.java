package com.delivery.deliveryapi.model;

import com.delivery.deliveryapi.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Setter@Getter@NoArgsConstructor
@Embeddable
public class Customer{

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    List<Order> orders;


}
