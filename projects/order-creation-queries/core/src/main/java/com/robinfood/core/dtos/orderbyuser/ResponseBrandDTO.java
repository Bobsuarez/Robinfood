package com.robinfood.core.dtos.orderbyuser;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBrandDTO {

    private Long brandMenuId;

    private Long id;

    private String image;

    private String name;

}
