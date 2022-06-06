package com.delivery.deliveryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class ResponseDto {
    String message;
    HttpStatus httpStatus;
}
