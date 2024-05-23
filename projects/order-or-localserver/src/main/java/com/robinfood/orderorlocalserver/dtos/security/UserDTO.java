package com.robinfood.orderorlocalserver.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDTO {

    private final String username;

    private final String password;

    private final Set<SimpleGrantedAuthority> roles;
}
