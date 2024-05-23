package com.robinfood.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class APITokenResponseEntity {

    private int code;
    private String messages;
    private TokenEntity result;
    private String status;
}
