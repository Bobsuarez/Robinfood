package com.robinfood.core.entities.transactionrequestentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PitsEntity {

    private String carPlate;

    private Boolean isActive;
}
