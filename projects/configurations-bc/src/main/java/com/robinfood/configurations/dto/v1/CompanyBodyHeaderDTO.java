package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.models.Company;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CompanyBodyHeaderDTO implements Serializable {

    private static final long serialVersionUID = 4637180375121285617L;

    @NotBlank
    @Schema(example = "COP")
    @Size(min = 1, max = 3)
    @JsonProperty("currency_type")
    public String currencyType;

    @NotBlank
    @Schema(example = "$")
    @Size(min = 1, max = 5)
    @JsonProperty("currency_symbol")
    public String currencySymbol;

    @NotBlank
    @Schema(example = "1")
    @Size(min = 1, max = 10)
    @JsonProperty("id")
    public Long id;

    @NotBlank
    @Schema(example = "901131317-1")
    @JsonProperty("identification")
    @Size(min = 1, max = 50)
    public String identification;

    @NotBlank
    @Schema(example = "RobinFood Colombia SAS")
    @Size(min = 1, max = 45)
    @JsonProperty("name")
    public String name;

    @NotBlank
    @Schema(implementation = CountriesResponseDTO.class)
    @JsonProperty("country")
    public CountriesResponseDTO countriesResponseDTO;

    @NotBlank
    @Schema(example = "Status company")
    @JsonProperty("status")
    public Long status;

    public static CompanyBodyHeaderDTO fromCompanyRepository(Company companyFound) {
        return CompanyBodyHeaderDTO.builder()
                .currencyType(companyFound.getCurrencyType())
                .currencySymbol(companyFound.getCurrencySymbol())
                .id(companyFound.getId())
                .identification(companyFound.getIdentification())
                .name(companyFound.getName())
                .countriesResponseDTO(CountriesResponseDTO
                        .fromCountriesRepository(companyFound.getCountry()))
                .status(companyFound.getStatus())
                .build();
    }


}
