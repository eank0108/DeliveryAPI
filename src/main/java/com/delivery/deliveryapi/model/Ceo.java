package com.delivery.deliveryapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
@Getter@Setter
@Entity
public class Ceo extends Users {
    @OneToOne(mappedBy = "ceo")
    private Restaurant restaurant;
}
