package com.robinfood.paymentmethodsbc.dto.sso;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(hidden = true)
public class SSOUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("legacy_id")
    private Long legacyId;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("country_id")
    private Long countryId;
}
