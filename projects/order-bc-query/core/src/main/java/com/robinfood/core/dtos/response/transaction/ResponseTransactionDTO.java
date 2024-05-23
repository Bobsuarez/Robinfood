package com.robinfood.core.dtos.response.transaction;

import com.robinfood.core.dtos.OrderCouponDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseTransactionDTO {

    private final LocalDateTime createdAt;

    private List<OrderCouponDTO> coupons;

    private Long id;

    private final Boolean paid;

    private String uniqueIdentifier;

    private final Long userId;

    private final Double value;
}
