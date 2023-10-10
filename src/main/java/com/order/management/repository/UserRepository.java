package com.order.management.repository;

import com.order.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByMobileNumber(String MobileNumber);

}
