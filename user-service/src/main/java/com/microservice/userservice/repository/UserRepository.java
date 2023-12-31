package com.microservice.userservice.repository;

import com.microservice.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserById(Long userId);
}
