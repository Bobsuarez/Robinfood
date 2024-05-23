package com.robinfood.dtos.request.simba.lineas;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.util.serializer.BigDecimalSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValorCargoDescuentoDTO {

    @JsonProperty(value = "IdMoneda")
    private String idMoneda;

    @JsonProperty(value = "Value")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal value;
}
