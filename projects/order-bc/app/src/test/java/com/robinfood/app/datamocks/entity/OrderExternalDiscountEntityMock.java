package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderExternalDiscountEntity;

public class OrderExternalDiscountEntityMock {

    public OrderExternalDiscountEntity getDataDefault() {
        return new OrderExternalDiscountEntity(
              1L,
          1L,
            "MENU_ITEM_DISCOUNT",
            10000.0
        );
    }
}
