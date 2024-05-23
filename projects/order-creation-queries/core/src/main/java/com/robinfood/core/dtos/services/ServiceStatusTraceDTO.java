package com.robinfood.core.dtos.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class define detail of status trace (services)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ServiceStatusTraceDTO {

    private Long id;
    private String name;
}
