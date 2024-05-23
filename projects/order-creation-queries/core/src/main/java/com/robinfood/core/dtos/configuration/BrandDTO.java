package com.robinfood.core.dtos.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
public class BrandDTO {

    private final Long id;

    private final String name;
}
