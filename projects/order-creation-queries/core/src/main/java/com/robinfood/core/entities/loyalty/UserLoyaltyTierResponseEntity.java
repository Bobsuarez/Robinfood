package com.robinfood.core.entities.loyalty;

import lombok.Data;

@Data
public class UserLoyaltyTierResponseEntity {

    private final Long tierId;
    private final String tierName;
}
