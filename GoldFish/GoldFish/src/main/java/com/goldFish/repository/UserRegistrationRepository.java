package com.goldFish.repository;

import com.goldFish.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {

    Optional<UserRegistration> findByEmail(String email);
}
