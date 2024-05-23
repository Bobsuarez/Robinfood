package com.robinfood.dtos.request.simba.extensiones;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiosCompradorDTO {

    @JsonProperty(value = "InformacionBeneficiosComprador")
    private List<InformacionBeneficiosCompradorDTO> informacionBeneficiosComprador;
}
