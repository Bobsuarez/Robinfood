package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OrderFinalProductEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class OrderFinalProductEntityMock {

    public OrderFinalProductEntity getDataDefault() {
        final OrderFinalProductEntity orderFinalProductEntity = new OrderFinalProductEntity();

        orderFinalProductEntity.setArticleId(1L);
        orderFinalProductEntity.setArticleTypeId(1L);
        orderFinalProductEntity.setBrandId(1L);
        orderFinalProductEntity.setBrandMenuId(1L);
        orderFinalProductEntity.setBrandName("Muy");
        orderFinalProductEntity.setBasePrice(0.0);
        orderFinalProductEntity.setCompanyId(1L);
        orderFinalProductEntity.setCreatedAt(null);
        orderFinalProductEntity.setDiscountPrice(100.0);
        orderFinalProductEntity.setFinalProductCategoryId(1L);
        orderFinalProductEntity.setFinalProductCategoryName("Suggested");
        orderFinalProductEntity.setFinalProductId(1L);
        orderFinalProductEntity.setFinalProductName("Cubano");
        orderFinalProductEntity.setId(null);
        orderFinalProductEntity.setImage("default-muy.png");
        orderFinalProductEntity.setMenuHallProductId(1L);
        orderFinalProductEntity.setOrderId(1L);
        orderFinalProductEntity.setProductsPrice(100.0);
        orderFinalProductEntity.setQuantity(1);
        orderFinalProductEntity.setSizeId(1L);
        orderFinalProductEntity.setSizeName("MUY");
        orderFinalProductEntity.setSku("sku");
        orderFinalProductEntity.setCo2Total(BigDecimal.ZERO);
        orderFinalProductEntity.setTotal(0.0);
        orderFinalProductEntity.setTotalPriceNt(50.0);
        orderFinalProductEntity.setTotalTaxPrice(50.0);
        orderFinalProductEntity.setUpdatedAt(null);

        return orderFinalProductEntity;
    }

    public OrderFinalProductEntity getDataDefaultWithId() {
        final OrderFinalProductEntity orderFinalProductEntity = new OrderFinalProductEntity();

        orderFinalProductEntity.setArticleId(1L);
        orderFinalProductEntity.setArticleTypeId(1L);
        orderFinalProductEntity.setBrandId(1L);
        orderFinalProductEntity.setBrandMenuId(1L);
        orderFinalProductEntity.setBrandName("Muy");
        orderFinalProductEntity.setBasePrice(0.0);
        orderFinalProductEntity.setCompanyId(1L);
        orderFinalProductEntity.setCreatedAt(null);
        orderFinalProductEntity.setDiscountPrice(100.0);
        orderFinalProductEntity.setFinalProductCategoryId(1L);
        orderFinalProductEntity.setFinalProductCategoryName("Suggested");
        orderFinalProductEntity.setFinalProductId(1L);
        orderFinalProductEntity.setFinalProductName("Cubano");
        orderFinalProductEntity.setId(1L);
        orderFinalProductEntity.setImage("default-muy.png");
        orderFinalProductEntity.setMenuHallProductId(1L);
        orderFinalProductEntity.setOrderId(1L);
        orderFinalProductEntity.setProductsPrice(100.0);
        orderFinalProductEntity.setQuantity(1);
        orderFinalProductEntity.setSizeId(1L);
        orderFinalProductEntity.setSizeName("MUY");
        orderFinalProductEntity.setSku("sku");
        orderFinalProductEntity.setCo2Total(BigDecimal.ZERO);
        orderFinalProductEntity.setTotal(0.0);
        orderFinalProductEntity.setTotalPriceNt(50.0);
        orderFinalProductEntity.setTotalTaxPrice(50.0);
        orderFinalProductEntity.setUpdatedAt(null);

        return orderFinalProductEntity;
    }

    public List<OrderFinalProductEntity> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }

    public List<OrderFinalProductEntity> getDataDefaultListWithId() {
        return List.of(getDataDefaultWithId());
    }
}
