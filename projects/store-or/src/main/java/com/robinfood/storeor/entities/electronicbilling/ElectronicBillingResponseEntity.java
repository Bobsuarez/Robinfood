package com.robinfood.storeor.entities.electronicbilling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectronicBillingResponseEntity {

    private String createdAt;

    private String id;

    private String message;
}
