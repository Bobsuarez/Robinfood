package com.robinfood.core.entities.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class define detail of delivery courier (services)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDeliveryCourierEntity {

    private String name;
    private String phone;
    private String photoUrl;
    private String vehiclePlate;
}
