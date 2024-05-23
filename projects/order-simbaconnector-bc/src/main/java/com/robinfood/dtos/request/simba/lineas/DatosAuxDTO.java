package com.robinfood.dtos.request.simba.lineas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.dtos.request.orderbill.FinalProductTaxDTO;
import com.robinfood.util.serializer.BigDecimalSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatosAuxDTO {

    @JsonIgnore
    private Long finalProductId;

    @JsonIgnore
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal finalProductTaxes;

    @JsonIgnore
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal finalProductDiscount;

    @JsonIgnore
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal finalProductTotalPrice;

    @JsonIgnore
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal finalProductPercentage;

    @JsonIgnore
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal finalProductBaseImponible;

    @JsonIgnore
    private Long taxId;

    @JsonIgnore
    private List<FinalProductTaxDTO> finalProductTax;

    @JsonIgnore
    private BigDecimal precioUnitario;
}
