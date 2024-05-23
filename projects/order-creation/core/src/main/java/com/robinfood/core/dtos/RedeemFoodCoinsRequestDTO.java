package com.robinfood.core.dtos;

import com.robinfood.core.constants.GlobalConstants;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class RedeemFoodCoinsRequestDTO implements Serializable {

    private BigDecimal amount;

    private Long countryId;

    private FoodCoinsEntityRequestDTO entity;

    private final Long entityId = GlobalConstants.FOOD_COINS_ENTITY_ORDER;

    private Long extra1;

    private String extra2;

    private Long issuerId;

    private String issuerName;

    private Integer operationType;

    private Long sourceId;

    private Long userId;
}
