package com.robinfood.core.dtos.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CountryDTO {

    private final Long id;

    private final String name;

    private String timezone;
}
