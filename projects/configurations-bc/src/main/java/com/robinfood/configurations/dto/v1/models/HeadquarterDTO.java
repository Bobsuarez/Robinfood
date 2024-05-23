package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.AbstractBaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeadquarterDTO extends AbstractBaseDTO {

    private static final long serialVersionUID = -5952393082854027787L;

    @Schema(example = "3215467890")
    @JsonProperty(value = "phone")
    private String phone;

    @Schema(example = "headquarter@domain.com")
    @JsonProperty(value = "email")
    private String email;

    @Schema(example = "Career 43 # 12 - 13")
    @JsonProperty(value = "address")
    private String address;

    @Schema(example = "Career 43 # 12 - 13")
    @JsonProperty(value = "cityName")
    private String cityName;
}
