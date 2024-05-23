package com.robinfood.dtos.request.simba.terceros.tercero.ubicacionfisica;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubdivisionPaisDTO {

    @NonNull
    @JsonProperty(value = "Value")
    private String value;
}
