package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderFinalProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderFinalProductEntityMock {

    public static OrderFinalProductEntity getDataDefault() {

        return OrderFinalProductEntity.builder()
                .articleId((short) 1)
                .articleTypeId((short) 2)
                .brandId((short) 3)
                .brandMenuId((short) 4)
                .brandName("Brand Name")
                .basePrice(new BigDecimal("100.0"))
                .companyId((short) 5)
                .createdAt(LocalDateTime.now())
                .discountPrice(10.0)
                .finalProductCategoryId((short) 6)
                .finalProductCategoryName("Category Name")
                .finalProductId((short) 7)
                .finalProductName("Product Name")
                .id(1001L)
                .image("image_url")
                .menuHallProductId(2001L)
                .orderId(12345)
                .productsPrice(90.0)
                .quantity((short) 1)
                .sizeId((short) 8)
                .sizeName("Size Name")
                .sku("SKU123")
                .co2Total(new BigDecimal("1.5"))
                .co2CompensationPriceTotal(new BigDecimal("1.0"))
                .total(100.0)
                .totalPriceNt(95.0)
                .totalTaxPrice(5.0)
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
