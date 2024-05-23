package com.robinfood.core.entities.changestateorderequestentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeStateOrderRespondEntity {

    private String couponReference;

    private String dateTime;

    private String integrationId;

    private Long orderId;

    private Long orderUid;

    private String statusCode;
}
