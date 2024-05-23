package com.robinfood.ordereports_bc_muyapp.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFinalProductGroupDTO {

    private Short id;

    private List<ResponseGroupIngredientDTO> ingredients;

    private String name;

}
