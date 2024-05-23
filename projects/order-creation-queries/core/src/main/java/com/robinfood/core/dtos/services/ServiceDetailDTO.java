package com.robinfood.core.dtos.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class define detail of service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ServiceDetailDTO {

    private Long id;
    private String referenceUid;
    private Long referenceId;
    private String integrationId;
    private boolean synced;
    private ServiceStatusTraceDTO statusTrace;
    private ServiceDeliveryCourierDTO deliveryCourier;
}
