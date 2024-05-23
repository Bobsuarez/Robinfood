package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateStoreDTO implements Serializable {

    private static final long serialVersionUID = 4547180375121285617L;

    @Schema(example = "new store")
    @JsonProperty(value = "name")
    private String name;

    @Schema(example = "internal name store")
    @JsonProperty(value = "internalName")
    private String internalName;

    @Schema(example = "1")
    @JsonProperty(value = "identifier")
    private String identifier;

    @Schema(example = "0")
    @JsonProperty(value = "companyCountryId")
    private Long companyId;

    @Schema(example = "0")
    @JsonProperty(value = "cityId")
    private Long cityId;

    @Schema(example = "0")
    @JsonProperty(value = "zoneId")
    private Long zoneId;

    @Schema(example = "0")
    @JsonProperty(value = "storeTypeId")
    private Long storeTypeId;

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

    @Schema(example = "122")
    @JsonProperty(value = "storeIdentifierTypeId")
    private Long storeIdentifierTypeId;

    @JsonProperty(value = "currencyType")
    private String currencyType;

    @JsonProperty(value = "currencySymbol")
    private String currencySymbol;

    @JsonProperty(value = "taxRegime")
    private String taxRegime;

    @JsonProperty(value = "uuid")
    private String uuid;
}
