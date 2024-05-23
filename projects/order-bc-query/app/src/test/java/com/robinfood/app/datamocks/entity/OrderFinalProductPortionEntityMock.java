package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderFinalProductPortionEntity;

import java.util.Collections;
import java.util.List;

public class OrderFinalProductPortionEntityMock {

    public OrderFinalProductPortionEntity getDataDefault() {

        final OrderFinalProductPortionEntity orderFinalProductPortion = new OrderFinalProductPortionEntity();

        orderFinalProductPortion.setAddition(true);
        orderFinalProductPortion.setBasePrice(0.0);
        orderFinalProductPortion.setCompanyId(1L);
        orderFinalProductPortion.setChangedProduct(false);
        orderFinalProductPortion.setCreatedAt(null);
        orderFinalProductPortion.setDiscount(0.0);
        orderFinalProductPortion.setDicUnitId(1L);
        orderFinalProductPortion.setEffectiveSale(0);
        orderFinalProductPortion.setGroupId(1L);
        orderFinalProductPortion.setGroupName("Escoge tu base");
        orderFinalProductPortion.setGroupSku("12276489554839470634");
        orderFinalProductPortion.setId(1L);
        orderFinalProductPortion.setOperationDate("2022-03-10");
        orderFinalProductPortion.setOrderId(1234L);
        orderFinalProductPortion.setOrderFinalProductId(1L);
        orderFinalProductPortion.setPortionId(1L);
        orderFinalProductPortion.setPortionName("Ninguno");
        orderFinalProductPortion.setPortionSku("12276489554839470356");
        orderFinalProductPortion.setPortionStatus(0);
        orderFinalProductPortion.setProductId(1L);
        orderFinalProductPortion.setProductName("Ninguno");
        orderFinalProductPortion.setQuantity(0);
        orderFinalProductPortion.setQuantityFree(0);
        orderFinalProductPortion.setStoreId(1L);
        orderFinalProductPortion.setUnitsNumber(0.0);
        orderFinalProductPortion.setUpdatedAt(null);

        return orderFinalProductPortion;

    }

    public List<OrderFinalProductPortionEntity> getDataDefaultList() {
        return Collections.singletonList(getDataDefault());
    }
}
