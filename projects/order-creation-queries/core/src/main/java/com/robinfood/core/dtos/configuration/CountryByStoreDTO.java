package com.robinfood.core.dtos.configuration;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CountryByStoreDTO {

    private final Long id;

    private final String name;
}
