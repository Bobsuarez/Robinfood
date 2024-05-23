package com.robinfood.core.dtos;

import java.io.Serializable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@AllArgsConstructor
public class UserDTO  implements Serializable {

    private String password;

    private Set<SimpleGrantedAuthority> roles;

    private String username;
}
