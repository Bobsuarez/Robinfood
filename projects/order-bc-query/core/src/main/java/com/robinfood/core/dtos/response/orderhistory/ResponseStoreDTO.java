package com.robinfood.core.dtos.response.orderhistory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStoreDTO {

    private Long id;

    private String image;

    private String name;

}
