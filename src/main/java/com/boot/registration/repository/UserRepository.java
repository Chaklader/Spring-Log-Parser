package com.boot.registration.repository;

import com.boot.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Chaklader on Sep, 2017
 */
public interface UserRepository extends JpaRepository<User, Long>{

    User findUserByUsername(String username);
}
