package com.robinfood.changestatusor.models.retrofit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ResultResponse {

    @JsonProperty("access_token")
    private String access_token;

    @JsonProperty("expires_in")
    private Long expires_in;
}
