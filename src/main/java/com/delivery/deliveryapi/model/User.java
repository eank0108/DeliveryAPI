package com.delivery.deliveryapi.model;

import com.delivery.deliveryapi.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

// @Entity

@Setter@Getter@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends Timestamped {
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

    @Embedded
    private Customer customer;
    @Embedded
    private Ceo ceo;

    public User(UserRequestDto userRequestDto) {
        this.username = userRequestDto.getUsername();
        this.password = userRequestDto.getPassword();
        this.role = userRequestDto.getRoleEnum();
    }
}
