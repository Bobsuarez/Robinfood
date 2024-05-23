package com.robinfood.localorderbc.dtos.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CredentialsDTO {

    private String email;

    private String password;
}
