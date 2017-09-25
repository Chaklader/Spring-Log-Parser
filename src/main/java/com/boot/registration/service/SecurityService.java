package com.boot.registration.service;

/**
 * Created by Chaklader on Sep, 2017
 */
public interface SecurityService {

    String findLoggedinUsername();

    void autoLogin(String username, String password);
}
