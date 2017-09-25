package com.boot.registration.service;

import com.boot.registration.entity.User;

/**
 * Created by Chaklader on Sep, 2017
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
