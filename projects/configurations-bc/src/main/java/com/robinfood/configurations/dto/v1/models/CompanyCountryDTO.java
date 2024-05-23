package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.models.CompanyBrand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.robinfood.configurations.constants.BrandsConstants.BRAND_LOGO_BASE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCountryDTO {

    @Min(0)
    @Schema(example = "0")
    @JsonProperty(value = "companyCountryId")
    private Long companyCountryId;

    @NotBlank
    @Size(min = 1, max = 50)
    @Schema(example = "brand name")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @Size(min = 1, max = 50)
    @Schema(example = "brand Logo")
    @JsonProperty(value = "logo")
    private String logo;

    public static CompanyCountryDTO fromBrandCompanyChannel(CompanyBrand companyBrand) {
        return CompanyCountryDTO.builder()
                .companyCountryId(companyBrand.getCompany().getCountry().getId())
                .name(companyBrand.getName())
                .logo(BRAND_LOGO_BASE.concat(companyBrand.getLogo()))
                .build();
    }
}
