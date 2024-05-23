package com.robinfood.core.dtos.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class define detail of delivery courier (services)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ServiceDeliveryCourierDTO {

    private String name;
    private String phone;
    private String photoUrl;
    private String vehiclePlate;
}
