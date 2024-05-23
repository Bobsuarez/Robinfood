package com.robinfood.ordereports.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeliveryCourierServiceDTO implements Serializable {

    private CourierDTO deliveryCourier;

    private Long id;

    private String integrationId;

    private ProviderDTO provider;

    private Long referenceId;

    private String referenceUid;

    private String referenceUuid;

    private StatusDTO status;

    private Boolean synced;
}
