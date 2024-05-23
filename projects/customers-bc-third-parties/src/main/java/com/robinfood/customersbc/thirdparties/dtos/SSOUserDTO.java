package com.robinfood.customersbc.thirdparties.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(hidden = true)
public class SSOUserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5173777860468678065L;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("legacy_id")
    private Integer legacyId;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("country_id")
    private Integer countryId;

    @JsonProperty("default_company_id")
    private Integer defaultCompanyId;

    @JsonProperty("company_id")
    private Integer companyId;
}
