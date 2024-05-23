package com.robinfood.core.mappers;

import com.robinfood.core.dtos.services.ServiceDeliveryCourierDTO;
import com.robinfood.core.dtos.services.ServiceDetailDTO;
import com.robinfood.core.dtos.services.ServiceStatusTraceDTO;
import com.robinfood.core.entities.services.ServiceDetailEntity;

import static java.util.Objects.nonNull;

public final class ServiceMappers {

    private ServiceMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static ServiceDetailDTO toServiceDetailDTO(ServiceDetailEntity serviceDetailEntity) {

        if (nonNull(serviceDetailEntity)) {
            ServiceDetailDTO serviceDetailDTO = ServiceDetailDTO.builder()
                .id(serviceDetailEntity.getId())
                .referenceUid(serviceDetailEntity.getReferenceUid())
                .referenceId(serviceDetailEntity.getReferenceId())
                .integrationId(serviceDetailEntity.getIntegrationId())
                .synced(serviceDetailEntity.isSynced())
                .statusTrace(
                    ServiceStatusTraceDTO.builder()
                        .id(serviceDetailEntity.getStatusTrace().getId())
                        .name(serviceDetailEntity.getStatusTrace().getName())
                        .build()
                )
                .build();

            if (nonNull(serviceDetailEntity.getDeliveryCourier())) {
                return serviceDetailDTO.toBuilder()
                    .deliveryCourier(
                        ServiceDeliveryCourierDTO.builder()
                            .name(serviceDetailEntity.getDeliveryCourier().getName())
                            .phone(serviceDetailEntity.getDeliveryCourier().getPhone())
                            .photoUrl(serviceDetailEntity.getDeliveryCourier().getPhotoUrl())
                            .vehiclePlate(serviceDetailEntity.getDeliveryCourier().getVehiclePlate())
                            .build()
                    )
                    .build();
            }

            return serviceDetailDTO;
        }

        return new ServiceDetailDTO();
    }

}
