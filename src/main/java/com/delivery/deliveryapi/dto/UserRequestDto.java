package com.delivery.deliveryapi.dto;

import com.delivery.deliveryapi.model.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class UserRequestDto {

    private String username;
    private String password;
    private String passwordCheck;
    private UserRoleEnum roleEnum;

}
