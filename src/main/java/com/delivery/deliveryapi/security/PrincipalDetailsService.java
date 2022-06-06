package com.delivery.deliveryapi.security;

import com.delivery.deliveryapi.model.Customer;
import com.delivery.deliveryapi.model.User;
import com.delivery.deliveryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public PrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        System.out.println(username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("가입 정보가 없습니다.");
            throw new UsernameNotFoundException("가입 정보가 없습니다.");
        }
        System.out.println(user.toString());

        return new PrincipalDetails(user);
    }


}
