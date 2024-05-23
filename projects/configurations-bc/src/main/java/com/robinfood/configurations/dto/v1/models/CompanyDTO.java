package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = 3311901123877852281L;

    @JsonProperty(value = "country")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CountryDTO country;

    @Schema(example = "company")
    @JsonProperty(value = "name")
    private String name;

    @Column(name = "identification", nullable = false)
    private String identification;

    @JsonProperty(value = "identificationNumber")
    private String identificationNumber;

    @JsonProperty(value = "headquarter")
    private HeadquarterDTO headquarter;

    @JsonProperty(value = "holding")
    private HoldingDTO holding;

    @JsonProperty(value = "currency_type")
    private String currencyType;

    @JsonProperty(value = "currency_symbol")
    private String currencySymbol;


}
