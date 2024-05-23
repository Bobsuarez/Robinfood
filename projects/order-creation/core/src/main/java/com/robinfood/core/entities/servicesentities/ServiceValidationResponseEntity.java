package com.robinfood.core.entities.servicesentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class ServiceValidationResponseEntity {

    private String code;

    private ServiceResponseError error;

    private String message;
}
