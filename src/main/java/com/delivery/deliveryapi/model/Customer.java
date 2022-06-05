package com.delivery.deliveryapi.model;

import com.delivery.deliveryapi.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter@Getter@NoArgsConstructor
@Entity
public class Customer extends Users{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    List<Orders> orders;

    public Customer(UserRequestDto userRequestDto) {
        super.setUsername(userRequestDto.getUsername());
        super.setPassword(userRequestDto.getPassword());
        super.setRole(UserRoleEnum.USER);
    }
}
