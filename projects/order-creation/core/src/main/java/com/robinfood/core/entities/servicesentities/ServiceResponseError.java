package com.robinfood.core.entities.servicesentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class ServiceResponseError {

    private String code;

    private List<ServiceResponseErrorDetailsEntity> details;

    private String type;
}
