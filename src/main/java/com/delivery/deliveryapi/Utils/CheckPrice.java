package com.delivery.deliveryapi.Utils;

import org.springframework.stereotype.Component;

@Component
public class CheckPrice {
    public void checkPrice(String checkMoney, Integer div, Integer min, Integer max) throws IllegalArgumentException {

        Long money;
        try {
            money = Long.parseLong(checkMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해 주세요");
        }
        if (money % div != 0) {
            throw new IllegalArgumentException(div + "원 단위로 입력해 주세요");
        }
        if (money < min || money > max) {
            throw new IllegalArgumentException("허용범위를 벗어났습니다.");
        }


    }



}
