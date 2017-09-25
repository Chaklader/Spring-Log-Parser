package com.boot.registration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


/**
 * Created by Chaklader on Sep, 2017
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public String findLoggedinUsername() {

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();

        return userDetails instanceof UserDetails ?
                ((UserDetails) userDetails).getUsername() : null;
    }

    @Override
    public void autoLogin(String username, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken upAuthenToken = new UsernamePasswordAuthenticationToken(userDetails,
                password, userDetails.getAuthorities());

        authenticationManager.authenticate(upAuthenToken);

        // if the token is authenticated
        if (upAuthenToken.isAuthenticated()) {

            SecurityContextHolder.getContext().setAuthentication(upAuthenToken);
            LOGGER.debug(String.format("Auto login successful for %username", username));
        }
    }
}
