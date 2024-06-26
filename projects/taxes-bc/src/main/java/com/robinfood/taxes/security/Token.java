package com.robinfood.taxes.security;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token implements Serializable {

    private static final long serialVersionUID = -807314288026466512L;

    private String token;

    private long expiresIn;

}
