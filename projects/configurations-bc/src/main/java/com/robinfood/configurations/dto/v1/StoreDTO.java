package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.models.CityDTO;
import com.robinfood.configurations.dto.v1.models.CompanyDTO;
import com.robinfood.configurations.dto.v1.models.CountryDTO;
import com.robinfood.configurations.dto.v1.models.StoreMultiBrandDTO;
import com.robinfood.configurations.dto.v1.models.StateDTO;
import com.robinfood.configurations.dto.v1.models.StoreIdentifierTypeDTO;
import com.robinfood.configurations.dto.v1.models.StoreTypeDTO;
import com.robinfood.configurations.dto.v1.models.ZoneDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class StoreDTO implements Serializable {

    private static final long serialVersionUID = 3311809113877800004L;

    @Schema(example = "Id 123")
    @JsonProperty(value = "id")
    private Long id;

    @Schema(example = "store 123")
    @JsonProperty(value = "name")
    private String name;

    @Schema(example = "location")
    @JsonProperty(value = "location")
    private String location;

    @Schema(example = "3507769812")
    @JsonProperty(value = "phone")
    private String phone;

    @Schema(example = "store@domain.com")
    @JsonProperty(value = "email")
    private String email;

    @Schema(example = "Av. 20 # 33 - 12")
    @JsonProperty(value = "address")
    private String address;

    @Schema(example = "Store 123")
    @JsonProperty(value = "internalName")
    private String internalName;

    @Schema(example = "1243225678")
    @JsonProperty(value = "identification")
    @JsonInclude(Include.NON_NULL)
    private String identification;

    @JsonProperty(value = "timezone")
    @JsonInclude(Include.NON_NULL)
    private String timezone;

    @JsonProperty(value = "city")
    private CityDTO city;

    @JsonProperty(value = "state")
    @JsonInclude(Include.NON_NULL)
    private StateDTO state;

    @JsonProperty(value = "flowTax")
    private StoreTaxDTO flowTax;

    @JsonProperty(value = "country")
    @JsonInclude(Include.NON_NULL)
    private CountryDTO country;

    @JsonProperty(value = "zone")
    private ZoneDTO zoneId;

    @JsonProperty(value = "company")
    private CompanyDTO company;

    @JsonProperty(value = "storeType")
    private StoreTypeDTO storeTypeId;

    @JsonProperty(value = "uuid")
    private String uuid;

    @JsonProperty(value = "storeIdentifierTypeId")
    private StoreIdentifierTypeDTO storeIdentifierTypeId;

    @JsonProperty(value = "currencyType")
    @JsonInclude(Include.NON_NULL)
    private String currencyType;

    @JsonProperty(value = "currencySymbol")
    @JsonInclude(Include.NON_NULL)
    private String currencySymbol;

    @JsonProperty(value = "postalCode")
    private String postalCode;

    @JsonProperty(value = "taxRegime")
    @JsonInclude(Include.NON_NULL)
    private String taxRegime;

    @JsonProperty(value = "multiBrand")
    @JsonInclude(Include.NON_NULL)
    private StoreMultiBrandDTO multiBrand;
}
