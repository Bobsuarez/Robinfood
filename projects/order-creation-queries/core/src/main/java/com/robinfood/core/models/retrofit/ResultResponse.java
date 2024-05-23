package com.robinfood.core.models.retrofit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResultResponse {

    private String accessToken;

    private Long expiresIn;

}
