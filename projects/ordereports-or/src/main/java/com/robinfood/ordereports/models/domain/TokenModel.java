package com.robinfood.ordereports.models.domain;

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
