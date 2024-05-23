package com.robinfood.core.entities.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class define detail of service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDetailEntity {

    private ServiceDeliveryCourierEntity deliveryCourier;

    private Long id;

    private String integrationId;

    private Long referenceId;

    private String referenceUid;

    private ServiceStatusTraceEntity statusTrace;

    private boolean synced;

}
