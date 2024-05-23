package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductDiscountDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductGroupDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductTaxDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductDiscountEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductGroupEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductTaxEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.orderorlocalserver.constants.GlobalConstants.DEFAULT_STRING_VALUE;

public final class OrderDetailProductMappers {

    private OrderDetailProductMappers() {
        // this constructor is empty because it is a mapper class
    }

    private static List<OrderDetailProductDiscountDTO> convertFromEntityToDtoByOrderDetailProductDiscountDTO(
            List<OrderDetailProductDiscountEntity> orderDetailProductDiscountEntities
    ) {
        return Optional.ofNullable(orderDetailProductDiscountEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailProductDiscountMappers::toOrderDetailProductDiscountDTO)
                .collect(Collectors.toList());
    }

    private static List<OrderDetailProductGroupDTO> convertFromEntityToDtoByOrderDetailProductGroupDTO(
            List<OrderDetailProductGroupEntity> orderDetailProductDiscountEntities
    ) {
        return Optional.ofNullable(orderDetailProductDiscountEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailProductGroupMappers::toOrderDetailProductGroupDTO)
                .collect(Collectors.toList());
    }

    private static List<OrderDetailProductTaxDTO> convertFromEntityToDtoByOrderDetailProductTaxDTO(
            List<OrderDetailProductTaxEntity> orderDetailProductTaxEntities
    ) {
        return Optional.ofNullable(orderDetailProductTaxEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailProductTaxMappers::toOrderDetailProductTaxMappers)
                .collect(Collectors.toList());
    }

    public static OrderDetailProductDTO toOrderDetailProductDTO(
            OrderDetailProductEntity orderDetailProductEntity
    ) {

        return OrderDetailProductDTO.builder()
                .articleId(orderDetailProductEntity.getArticleId())
                .articleName(DEFAULT_STRING_VALUE)
                .articleTypeId(orderDetailProductEntity.getArticleTypeId())
                .basePrice(orderDetailProductEntity.getBasePrice())
                .brandId(orderDetailProductEntity.getBrandId())
                .brandName(orderDetailProductEntity.getBrandName())
                .categoryId(orderDetailProductEntity.getCategoryId())
                .categoryName(orderDetailProductEntity.getCategoryName())
                .co2Total(orderDetailProductEntity.getCo2Total())
                .deduction(orderDetailProductEntity.getDeduction())
                .discount(orderDetailProductEntity.getDiscount())
                .discounts(convertFromEntityToDtoByOrderDetailProductDiscountDTO(
                        orderDetailProductEntity.getDiscounts()
                ))
                .finalProductId(orderDetailProductEntity.getFinalProductId())
                .groups(convertFromEntityToDtoByOrderDetailProductGroupDTO(orderDetailProductEntity.getGroups()))
                .id(orderDetailProductEntity.getId())
                .image(orderDetailProductEntity.getImage())
                .brandMenuId(orderDetailProductEntity.getBrandMenuId())
                .menuHallProductId(orderDetailProductEntity.getMenuHallProductId())
                .name(orderDetailProductEntity.getName())
                .quantity(orderDetailProductEntity.getQuantity())
                .sizeId(orderDetailProductEntity.getSizeId())
                .sizeName(orderDetailProductEntity.getSizeName())
                .sku(orderDetailProductEntity.getSku())
                .taxes(convertFromEntityToDtoByOrderDetailProductTaxDTO(orderDetailProductEntity.getTaxes()))
                .unitPrice(orderDetailProductEntity.getUnitPrice())
                .total(orderDetailProductEntity.getTotal())
                .build();
    }
}
