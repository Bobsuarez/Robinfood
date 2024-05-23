package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateStoreDTO implements Serializable {

    private static final long serialVersionUID = 4547180375121285617L;

    @NotBlank
    @Size(min = 1, max = 45)
    @Schema(example = "new store")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank
    @Size(min = 1, max = 45)
    @Schema(example = "internal name store")
    @JsonProperty(value = "internalName")
    private String internalName;

    @Schema(example = "1")
    @JsonProperty(value = "identifier")
    private String identifier;

    @Min(0)
    @Schema(example = "0")
    @JsonProperty(value = "companyCountryId")
    private Long companyCountryId;

    @Min(0)
    @Schema(example = "0")
    @JsonProperty(value = "cityId")
    private Long cityId;

    @Min(0)
    @Schema(example = "0")
    @JsonProperty(value = "zoneId")
    private Long zoneId;

    @Min(0)
    @Schema(example = "0")
    @JsonProperty(value = "storeTypeId")
    private Long storeTypeId;

    @NotBlank
    @Schema(example = "location")
    @JsonProperty(value = "location")
    private String location;

    @NotBlank
    @Schema(example = "3507769812")
    @JsonProperty(value = "phone")
    private String phone;

    @NotBlank
    @Schema(example = "store@domain.com")
    @JsonProperty(value = "email")
    private String email;

    @NotBlank
    @Schema(example = "Av. 20 # 33 - 12")
    @JsonProperty(value = "address")
    private String address;

    @Min(0)
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
