package com.robinfood.paymentmethodsbc.security;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    /**
     *
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        return new User(username, "NO_PASSWORD", new ArrayList<>());
    }
}
