package com.robinfood.core.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class EntityDTO<T> {

    @JsonInclude(Include.NON_NULL)
    private final List<T> items;

    @JsonInclude(Include.NON_NULL)
    private PaginationDTO pagination;

}
