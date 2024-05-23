package com.robinfood.configurations.dto.v1.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 4639258349503818423L;

    @Schema(example = "1")
    @JsonProperty(value = "id")
    private Long id;

    @Schema(example = "57311000000")
    @JsonProperty(value = "phone")
    private String phone;

    @Schema(example = "true")
    @JsonProperty(value = "allow_admin")
    private Boolean allowAdmin;

    @Schema(example = "1")
    @JsonProperty(value = "default_company_id")
    private Long defaultCompanyId;

    @Schema(example = "123456")
    @JsonProperty(value = "legacy_id")
    private Long legacyId;

    @Schema(example = "Firstname")
    @JsonProperty(value = "first_name")
    private String firstName;

    @Schema(example = "Lastname")
    @JsonProperty(value = "last_name")
    private String lastName;

    @Schema(example = "email@example.com")
    @JsonProperty(value = "email")
    private String email;

    @Schema(example = "1")
    @JsonProperty(value = "country_id")
    private Long countryId;

    @JsonProperty(value = "companies")
    private transient List<CompanyDTO> companies;

}
