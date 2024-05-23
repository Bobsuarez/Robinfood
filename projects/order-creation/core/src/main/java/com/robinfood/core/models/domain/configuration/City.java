package com.robinfood.core.models.domain.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class City {
    private String code;
    private Long id;
    private String name;
}
