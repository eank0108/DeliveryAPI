package com.delivery.deliveryapi.security;

import com.delivery.deliveryapi.model.Customer;
import com.delivery.deliveryapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PrincipalDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Autowired
    public PrincipalDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            System.out.println("가입 정보가 없습니다.");
            throw new UsernameNotFoundException("가입 정보가 없습니다.");
        }

        return new PrincipalDetails(customer);
    }
}
