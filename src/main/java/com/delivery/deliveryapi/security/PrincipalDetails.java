package com.delivery.deliveryapi.security;

import com.delivery.deliveryapi.model.Customer;
import com.delivery.deliveryapi.model.UserRoleEnum;
import com.delivery.deliveryapi.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {
    private final String username;
    private final String password;
    private final UserRoleEnum roleEnum;


    public PrincipalDetails(Users users) {
        this.username = users.getUsername();
        this.password = users.getPassword();
        this.roleEnum = users.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roleEnum.getAuthority()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
