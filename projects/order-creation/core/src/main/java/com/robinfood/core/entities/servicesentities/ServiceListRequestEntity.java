package com.robinfood.core.entities.servicesentities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class ServiceListRequestEntity {

    @JsonProperty("stores")
    private List<ServiceRequestEntity> stores;

}
