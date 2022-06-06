package com.delivery.deliveryapi.controller;

import com.delivery.deliveryapi.dto.ResponseDto;
import com.delivery.deliveryapi.dto.UserRequestDto;
import com.delivery.deliveryapi.dto.UserResponseDto;
import com.delivery.deliveryapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/user/login")
    public ResponseEntity<Object> login(@RequestBody UserRequestDto userRequestDto) {
        return customerService.login(userRequestDto);
    }

    @PostMapping("/user/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRequestDto userRequestDto) {
        return customerService.registerUser(userRequestDto);
    }
}
