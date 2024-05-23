package com.robinfood.paymentmethodsbc.security;

import java.util.Collection;

import com.robinfood.paymentmethodsbc.dto.sso.SSOUserDTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SessionUser extends User {
    private final SSOUserDTO ssoUser;

    public SessionUser(
        String username,
        String password,
        SSOUserDTO ssoUser,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
        this.ssoUser = ssoUser;
    }

    public SSOUserDTO getSsoUser() {
        return ssoUser;
    }
}
