package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderFiscalIdentifierDTO {

    @JsonProperty("identifier")
    private String fiscalIdentifier;
}
