package com.delivery.deliveryapi.security;

import com.delivery.deliveryapi.model.UserRoleEnum;
import com.delivery.deliveryapi.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {
    private final String id;
    private final String username;
    private final String role;


    public PrincipalDetails(User user) {
        this.username = user.getUsername();
        this.role = user.getRole().getAuthority();
        this.id = user.getId().toString();
    }
    public PrincipalDetails(String username, String user_id, String role) {
        this.username = username;
        this.id = user_id;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return "password";
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
