package com.robinfood.taxes.dto.v1.create;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateFamilyDTO")
public class CreateFamilyDTO implements Serializable {

    private static final long serialVersionUID = -2520508378974090004L;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 1, max = 45, message = "Length of name must be between 1 and 45 characters.")
    @JsonProperty
    @Schema(example = "Name")
    private String name;

    @Min(value = 1, message = "Country ID must be greater than zero.")
    @JsonProperty("country_id")
    @Schema(example = "1")
    private Long countryId;

    @Min(value = 1, message = "Family Type ID must be greater than zero.")
    @JsonProperty("family_type_id")
    @Schema(example = "2")
    private Long familyTypeId;

}
