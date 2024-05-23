package com.robinfood.core.dtos

import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserDTO (
    private var username: String,
    private var password: String,
    private var roles: Set<SimpleGrantedAuthority>
)