package com.robinfood.core.entities.menuvalidationentities;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MenuValidationEntity {

    private final Long brandId;

    private final Long countryId;

    private final Long flowId;

    private final MenuValidationPaymentEntity payment;

    private final Long platformId;

    private final List<MenuValidationProductEntity> products;

    private final Long storeId;

    private final String uuid;
}
