package com.delivery.deliveryapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Getter@Setter
@Embeddable
public class Ceo {
    @OneToOne
    private Restaurant restaurant;
}
