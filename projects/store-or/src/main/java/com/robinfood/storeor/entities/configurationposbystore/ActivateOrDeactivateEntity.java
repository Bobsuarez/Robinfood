package com.robinfood.storeor.entities.configurationposbystore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ActivateOrDeactivateEntity {

    private final Boolean status;
}
