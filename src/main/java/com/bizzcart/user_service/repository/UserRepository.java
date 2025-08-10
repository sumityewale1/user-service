package com.bizzcart.user_service.repository;

import com.bizzcart.user_service.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find user by email (unique)
    Optional<User> findByEmail(String email);

    // Find user by phone (unique)
    Optional<User> findByPhone(String phone);

    // Find user by name (if needed)
    Optional<User> findByName(String name);
}
