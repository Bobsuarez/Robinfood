package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.models.Headquarter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CompanyHeadquarterDTO {

    private static final long serialVersionUID = -5952393082854027787L;

    @Schema(example = "MFM1906048G1")
    @JsonProperty(value = "identification")
    private String identification;

    @Schema(example = "RobinFood Colombia SAS")
    @JsonProperty(value = "companyName")
    private String companyName;

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

    public static CompanyHeadquarterDTO fromHeadquarter(Headquarter headquarter) {
        return CompanyHeadquarterDTO.builder()
            .identification(headquarter.getCompany().getIdentification())
            .companyName(headquarter.getCompany().getName())
            .phone(headquarter.getPhone())
            .email(headquarter.getEmail())
            .address(headquarter.getAddress())
            .cityName(headquarter.getCityName())
            .build();
    }
}
