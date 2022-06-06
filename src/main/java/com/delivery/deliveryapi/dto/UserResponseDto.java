package com.delivery.deliveryapi.dto;

import com.delivery.deliveryapi.model.Customer;
import com.delivery.deliveryapi.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@NoArgsConstructor
public class UserResponseDto {
    private Long id;

    private String username;

    private String role;

    public UserResponseDto(User user) {
        username = user.getUsername();
        role = user.getRole().getAuthority();
    }
}
