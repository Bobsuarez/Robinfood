package com.robinfood.core.dtos.orderbyuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ResponseFinalProductGroupDTO {

    private Long id;

    private List<ResponseGroupIngredientDTO> ingredients;

    private String name;

}
