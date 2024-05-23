package com.robinfood.core.entities.servicesentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class ServiceResponseErrorDetailsEntity {

    private String error;

    private String field;

    private Long key;
}
