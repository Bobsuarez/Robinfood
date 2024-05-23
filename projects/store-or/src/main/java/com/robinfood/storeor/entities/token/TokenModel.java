package com.robinfood.storeor.entities.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenModel {

    private String accessToken;

    private Long expiresIn;

}
