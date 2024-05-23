package com.robinfood.localserver.commons.dtos.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TokenModelDTO {

    private String accessToken;

    private Long expiresIn;
}
