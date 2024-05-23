package com.robinfood.core.entities;

import static com.robinfood.core.constants.GlobalConstants.FOOD_COINS_ENTITY_ORDER;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RedeemFoodCoinsRequestEntity {

    private final BigDecimal amount;

    private final Long countryId;

    private final FoodCoinsEntityRequestEntity entity;

    private final Long entityId = FOOD_COINS_ENTITY_ORDER;

    @SerializedName("extra_1")
    private final Long extra1;

    @SerializedName("extra_2")
    private final String extra2;

    private final Long issuerId;

    private final String issuerName;

    private final Integer operationType;

    private final Long sourceId;

    private final Long userId;
}
