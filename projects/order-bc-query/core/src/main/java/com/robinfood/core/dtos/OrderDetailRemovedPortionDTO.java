package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "orderFinalProductId" })
public class OrderDetailRemovedPortionDTO {

    private final Long id;

    @JsonIgnore
    private final Long groupId;

    private final String name;

    private final Long orderFinalProductId;
}
