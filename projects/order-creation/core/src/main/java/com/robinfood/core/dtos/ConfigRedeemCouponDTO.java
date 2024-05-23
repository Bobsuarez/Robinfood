package com.robinfood.core.dtos;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ConfigRedeemCouponDTO implements Serializable {

    private final List<Long> redeemIds;
}
