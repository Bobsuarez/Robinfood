package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderFinalProductPortionEntity;

import java.time.LocalDateTime;

public class OrderFinalProductPortionEntityMock {

    public static OrderFinalProductPortionEntity getDataDefault() {

        return OrderFinalProductPortionEntity.builder()
                .addition(true)
                .basePrice(10.0)
                .companyId((short) 1)
                .changedProduct(false)
                .createdAt(LocalDateTime.now())
                .discount(2.0)
                .dicUnitId((short) 2)
                .effectiveSale(100)
                .groupId((short) 3)
                .groupName("Group Name")
                .groupSku("GRPSKU123")
                .id(1001L)
                .operationDate("2024-06-20")
                .orderId(12345)
                .orderFinalProductId(2001L)
                .portionId((short) 4)
                .portionName("Portion Name")
                .portionSku("PRTSKU123")
                .portionStatus(1)
                .productId((short) 5)
                .productName("Product Name")
                .quantity(2)
                .quantityFree((short) 0)
                .storeId((short) 6)
                .unitsNumber(1.0)
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
