package com.robinfood.customersbc.thirdparties.security;

import com.robinfood.customersbc.thirdparties.dtos.SSOUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

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
