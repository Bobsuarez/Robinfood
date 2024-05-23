package com.robinfood.localserver.commons.dtos.searchallorders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderResponseDTO {
    private String brandLogo;
    private String date;
    private Boolean delivery;
    private String mobile;
    private String name;
    private String order;
    private Long orderId;
    private Long originId;
    private String orderUuid;
    private Boolean printTicket;
    private Long status;
    private String uid;
    private Long userLoyaltyStatus;
}
