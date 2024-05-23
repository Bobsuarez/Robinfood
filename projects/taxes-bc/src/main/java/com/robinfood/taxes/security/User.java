package com.robinfood.taxes.security;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@AllArgsConstructor
public class User {

    private String id;
    private String username;
    private String password;
    private Set<SimpleGrantedAuthority> roles;

}
