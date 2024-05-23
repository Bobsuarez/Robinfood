package com.robinfood.storeor.dtos.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class TreasureDepartmentPaymentDTO {

    private List<TreasurePaymentEntitiesDTO> entities;

    private final Long id;

    private final String name;
}
