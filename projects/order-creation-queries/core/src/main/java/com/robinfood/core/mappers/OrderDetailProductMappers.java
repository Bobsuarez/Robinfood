package com.robinfood.core.mappers;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailProductDiscountDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupDTO;
import com.robinfood.core.dtos.OrderDetailProductTaxDTO;
import com.robinfood.core.entities.OrderDetailProductDiscountEntity;
import com.robinfood.core.entities.OrderDetailProductEntity;
import com.robinfood.core.entities.OrderDetailProductGroupEntity;
import com.robinfood.core.entities.OrderDetailProductTaxEntity;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
