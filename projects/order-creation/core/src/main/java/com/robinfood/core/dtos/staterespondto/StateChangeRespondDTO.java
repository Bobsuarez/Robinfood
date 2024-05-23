package com.robinfood.core.dtos.staterespondto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateChangeRespondDTO {

    private String brandId;

    private String couponReference;

    private String dateTime;

    private String integrationId;

    private Long orderId;

    private Long orderUuid;

    private String statusCode;
}
