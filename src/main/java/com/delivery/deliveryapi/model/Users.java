package com.delivery.deliveryapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// @Entity
@MappedSuperclass
@Setter@Getter
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Users extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

}
