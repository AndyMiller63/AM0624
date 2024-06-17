package com.windmill.rentalservice.repository;

import com.windmill.rentalservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    //Optional<User> findUserByUserId(@Param("userId") Long user_id);
}
