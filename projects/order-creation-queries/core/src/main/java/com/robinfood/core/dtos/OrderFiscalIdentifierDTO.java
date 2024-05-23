package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class OrderFiscalIdentifierDTO {

    private String identifier;
}
