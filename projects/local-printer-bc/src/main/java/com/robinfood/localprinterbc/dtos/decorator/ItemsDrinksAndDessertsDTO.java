package com.robinfood.localprinterbc.dtos.decorator;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ItemsDrinksAndDessertsDTO {

    private Boolean addition;

    private String name;

    private Integer quantity;
}
