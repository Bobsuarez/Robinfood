package com.robinfood.core.dtos.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StoresDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<StoreDTO> content;
}
