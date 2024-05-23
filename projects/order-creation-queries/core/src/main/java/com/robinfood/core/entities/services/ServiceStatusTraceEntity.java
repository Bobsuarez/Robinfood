package com.robinfood.core.entities.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class define detail of status trace (services)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceStatusTraceEntity {

    private Long id;
    private String name;
}
