package com.robinfood.storeor.enums;

import static com.robinfood.storeor.configs.constants.APIConstants.POS_BASIC_CODE;
import static com.robinfood.storeor.configs.constants.APIConstants.POS_DELIVERY_CODE;
import static com.robinfood.storeor.configs.constants.APIConstants.POS_PHYSICAL_CODE;

public enum DeliveryEnum {
    POS_BASIC(POS_BASIC_CODE, Boolean.FALSE),
    POS_DELIVERY(POS_DELIVERY_CODE, Boolean.TRUE),
    POS_PHYSICAL(POS_PHYSICAL_CODE, Boolean.FALSE);

    private static final DeliveryEnum[] status;

    static {
        status = values();
    }

    private final Long posId;

    private final Boolean isDelivery;

    DeliveryEnum(Long posId, Boolean isDelivery) {
        this.posId = posId;
        this.isDelivery = isDelivery;
    }

    public Long getPosId() {
        return posId;
    }

    public Boolean getDelivery() {
        return isDelivery;
    }

    public static DeliveryEnum valueOfIdPos(Long posId) {
        for (DeliveryEnum status : status) {
            if (status.posId.equals(posId)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching Status for [" + posId + "]");
    }

}
