package com.robinfood.core.entities;

import java.util.List;
import lombok.Data;

@Data
public class ConfigRedeemCouponEntity {

    private final List<Long> redeemIds;
}
