package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class EntityDTO<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<T> items;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaginationDTO pagination;
}
