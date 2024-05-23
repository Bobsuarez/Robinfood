package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDTO {

    private String username;

    private String password;

    private Set<SimpleGrantedAuthority> roles;
}
