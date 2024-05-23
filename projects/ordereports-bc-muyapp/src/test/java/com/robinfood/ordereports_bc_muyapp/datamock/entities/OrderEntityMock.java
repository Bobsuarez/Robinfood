package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class OrderEntityMock {

    public static OrderEntity getDataDefault() {
        return OrderEntity.builder()
                .billingResolutionId((short) 1)
                .brandId((short) 2)
                .brandName("Mock Brand")
                .companyId((short) 3)
                .createdAt(LocalDateTime.now())
                .currency("USD")
                .deliveryTypeId((short) 4)
                .discounts(10.0)
                .enabledTrigger((short) 5)
                .fullSynchronized((short) 1)
                .id(1)
                .localDate(LocalDate.now())
                .localTime(LocalTime.now())
                .numberFinalProducts((short) 6)
                .operationDate(LocalDate.now())
                .orderInvoiceNumber("INV123456")
                .orderNumber("ORD123456")
                .originId((short) 7)
                .originName("Mock Origin")
                .paid(true)
                .paymentModelId((short) 8)
                .pickupTime("12:00:00")
                .posId((short) 9)
                .printed(false)
                .statusId((short) 10)
                .storeId((short) 11)
                .storeName("Mock Store")
                .subtotal(100.0)
                .taxes(20.0)
                .transactionId(123456789L)
                .co2Total(new BigDecimal("1.23"))
                .total(120.0)
                .uid("uid123456")
                .uuid("uuid123456")
                .updatedAt(LocalDateTime.now())
                .userId(123L)
                .workshiftId(456L)
                .build();
    }
}