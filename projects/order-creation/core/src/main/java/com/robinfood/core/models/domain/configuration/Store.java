package com.robinfood.core.models.domain.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Store {

    private String code;

    private Long id;

    private Long posId;

    private String postalCode;

    private Long posTypeId;
}
