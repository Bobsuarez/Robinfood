package com.robinfood.app.mappers;

import com.robinfood.core.dtos.OrderAddressDTO;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseBrandDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseFinalProductDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseFinalProductGroupDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseGroupIngredientDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderAddressDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderDetailDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderStatusDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOrderTraceDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseOriginDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponsePaymentDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponsePaymentDiscountDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponsePaymentMethodDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseServiceDTO;
import com.robinfood.core.dtos.response.orderhistory.ResponseStoreDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import com.robinfood.core.entities.OrderHistoryEntity;
import com.robinfood.core.entities.OrderServicesEntity;
import com.robinfood.core.entities.PaymentMethodEntity;
import com.robinfood.core.entities.ServiceEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class OrderDetailOrderMapper {

    public static ResponseOrderDetailDTO mapOrderEntityToResponseDTO(OrderEntity order) {
        return ResponseOrderDetailDTO.builder()
            .id(order.getId())
            .uid(order.getUid())
            .paid(order.getPaid())
            .orderNumber(order.getOrderNumber())
            .paymentModelId(order.getPaymentModelId())
            .origin(
                ResponseOriginDTO.builder()
                    .companyId(order.getCompanyId())
                    .store(ResponseStoreDTO.builder()
                        .id(order.getStoreId())
                        .name(order.getStoreName())
                        .image(StringUtils.EMPTY)
                        .build()
                    )
                    .build()
            )
            .payment(
                ResponsePaymentDTO.builder()
                    .subtotal(order.getSubtotal())
                    .tax(order.getTaxes())
                    .discount(order.getDiscounts())
                    .total(order.getTotal())
                    .co2Total(order.getCo2Total())
                    .build()
            )
            .build();
    }

    public static ResponseFinalProductDTO mapFinalProductToResponseDTO(OrderFinalProductEntity finalProduct) {
        return ResponseFinalProductDTO.builder()
            .id(finalProduct.getId())
            .name(finalProduct.getFinalProductName())
            .image(finalProduct.getImage())
            .price(finalProduct.getBasePrice())
            .value(finalProduct.getTotal())
            .quantity(finalProduct.getQuantity())
            .co2Total(finalProduct.getCo2Total())
            .brandId(finalProduct.getBrandId())
            .build();
    }

    public static List<ResponseFinalProductGroupDTO> mapFinalProductGroupsToResponseDTO(
        List<OrderFinalProductPortionEntity> portions
    ) {
        return portions.stream()
            .collect(
                Collectors.groupingBy(OrderFinalProductPortionEntity::getGroupId)
            )
            .values()
            .stream()
            .map(
                OrderDetailOrderMapper::mapGroupToResponseDTO
            )
            .collect(Collectors.toList());
    }

    public static ResponseFinalProductGroupDTO mapGroupToResponseDTO(
        List<OrderFinalProductPortionEntity> portions
    ) {
        Long groupId = portions.stream()
            .map(OrderFinalProductPortionEntity::getGroupId)
            .findAny()
            .orElseThrow(() -> new GenericOrderBcException("Group not found"));

        String groupName = portions.stream()
            .map(OrderFinalProductPortionEntity::getGroupName)
            .findAny()
            .orElseThrow(() -> new GenericOrderBcException("Group not found"));

        return ResponseFinalProductGroupDTO.builder()
            .id(groupId)
            .name(groupName)
            .ingredients(
                portions.stream()
                    .map(OrderDetailOrderMapper::mapIngredientToResponseDTO)
                    .collect(Collectors.toList())
            )
            .build();
    }

    public static ResponseGroupIngredientDTO mapIngredientToResponseDTO(
        OrderFinalProductPortionEntity portions
    ) {
        return ResponseGroupIngredientDTO.builder()
            .id(portions.getId())
            .name(portions.getPortionName())
            .image(StringUtils.EMPTY)
            .value(portions.getBasePrice())
            .build();
    }

    public static ResponseOrderStatusDTO mapOrderStatusToResponseDTO(
        OrderHistoryEntity orderHistory,
        OrderStatusDTO status
    ) {
        return ResponseOrderStatusDTO.builder()
            .id(status.getId())
            .name(status.getName())
            .description(orderHistory.getObservation())
            .created(orderHistory.getCreatedAt())
            .build();
    }

    public static ResponseOrderTraceDTO mapOrderTraceToResponseDTO(
        OrderHistoryEntity orderHistory,
        OrderStatusDTO status
    ) {
        return ResponseOrderTraceDTO.builder()
            .id(status.getId())
            .name(status.getName())
            .description(orderHistory.getObservation())
            .datetime(orderHistory.getCreatedAt())
            .build();
    }

    public static ResponseServiceDTO mapOrderServiceToResponseDTO(
            OrderServicesEntity orderService,
            ServiceEntity service
    ) {
        return ResponseServiceDTO.builder()
                .discount(orderService.getDiscount())
                .id(service.getId())
                .name(service.getName())
                .subtotal(orderService.getPriceNt())
                .tax(orderService.getTaxPrice())
                .taxPercentage(orderService.getTaxPercentage())
                .total(orderService.getTotal())
                .build();
    }

    public static ResponseBrandDTO getBrandFinalProduct(OrderFinalProductEntity finalProduct) {
        return ResponseBrandDTO.builder()
            .brandMenuId(finalProduct.getBrandMenuId())
            .id(finalProduct.getBrandId())
            .name(finalProduct.getBrandName())
            .image(StringUtils.EMPTY)
            .build();
    }

    public static ResponsePaymentDiscountDTO mapOrderDiscountToResponseDTO(OrderDiscountDTO orderDiscount) {
        return ResponsePaymentDiscountDTO.builder()
            .id(orderDiscount.getId())
            .value(orderDiscount.getDiscountValue())
            .build();
    }

    public static ResponsePaymentMethodDTO mapOrderPaymentMethodToResponseDTO(
        OrderPaymentDTO orderPayment,
        PaymentMethodEntity paymentMethod
    ) {
        return ResponsePaymentMethodDTO.builder()
            .id(paymentMethod.getId())
            .name(paymentMethod.getName())
            .value(orderPayment.getValue())
            .build();
    }

    public static ResponseOrderAddressDTO mapToResponseOrderAddressDTO(OrderAddressDTO orderAddressDTO) {
        if (nonNull(orderAddressDTO)) {
            return ResponseOrderAddressDTO.builder()
                .address(orderAddressDTO.getAddress())
                .notes(orderAddressDTO.getNotes())
                .latitude(orderAddressDTO.getLatitude())
                .longitude(orderAddressDTO.getLongitude())
                .zipCode(orderAddressDTO.getZipCode())
                .build();
        }
        return null;
    }

}
