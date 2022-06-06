package com.delivery.deliveryapi.repository;

import com.delivery.deliveryapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
