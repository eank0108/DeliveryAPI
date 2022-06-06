package com.delivery.deliveryapi.controller;

import com.delivery.deliveryapi.model.User;
import com.delivery.deliveryapi.security.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public PrincipalDetails test(@AuthenticationPrincipal PrincipalDetails user) {
        System.out.println(user);
        // System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return user;
    }
}

