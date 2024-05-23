package com.robinfood.core.mappers;

import com.robinfood.core.dtos.CouponDTO;
import com.robinfood.core.dtos.ElectronicBillDTO;
import com.robinfood.core.dtos.OrderDeductionDTO;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.OrderDetailDiscountDTO;
import com.robinfood.core.dtos.OrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailServiceDTO;
import com.robinfood.core.dtos.OrderDetailUserDTO;
import com.robinfood.core.dtos.OrderFiscalIdentifierDTO;
import com.robinfood.core.entities.CouponEntity;
import com.robinfood.core.entities.ElectronicBillEntity;
import com.robinfood.core.entities.OrderDeductionEntity;
import com.robinfood.core.entities.OrderDetailDiscountEntity;
import com.robinfood.core.entities.OrderDetailEntity;
import com.robinfood.core.entities.OrderDetailPaymentMethodEntity;
import com.robinfood.core.entities.OrderDetailProductEntity;
import com.robinfood.core.entities.OrderDetailServiceEntity;
import com.robinfood.core.entities.OrderFiscalIdentifierEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class OrderDetailMappers {

    private OrderDetailMappers() {
        throw new IllegalStateException("Utility class");
    }

    private static OrderFiscalIdentifierDTO convertFromEntityToDtoByOrderFiscalIdentifierEntity(
            OrderFiscalIdentifierEntity orderFiscalIdentifierEntity
    ) {

        if (Objects.isNull(orderFiscalIdentifierEntity)) {
            return new OrderFiscalIdentifierDTO();
        }

        return OrderFiscalIdentifierMappers.toOrderFiscalIdentifierDTOMappers(
                orderFiscalIdentifierEntity
        );
    }

    private static List<OrderDetailDiscountDTO> convertFromEntityToDtoByOrderDetailDiscountEntities(
            List<OrderDetailDiscountEntity> orderDetailDiscountEntities
    ) {
        return Optional.ofNullable(orderDetailDiscountEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailDiscountMappers::toOrderDetailDiscountDTO)
                .collect(Collectors.toList());
    }

    private static List<OrderDetailPaymentMethodDTO> convertFromEntityToDtoByOrderDetailPaymentMethodEntities(
            List<OrderDetailPaymentMethodEntity> orderDetailPaymentMethodEntities
    ) {
        return Optional.ofNullable(orderDetailPaymentMethodEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailPaymentMethodMappers::toOrderDetailPaymentMethodDTO)
                .collect(Collectors.toList());
    }

    private static List<OrderDetailProductDTO> convertFromEntityToDtoByOrderDetailProductEntities(
            List<OrderDetailProductEntity> orderDetailProductEntities
    ) {
        return Optional.ofNullable(orderDetailProductEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailProductMappers::toOrderDetailProductDTO)
                .collect(Collectors.toList());
    }

    private static List<OrderDetailServiceDTO> convertFromEntityToDtoByOrderDetailServicesEntities(
            List<OrderDetailServiceEntity> orderDetailServiceEntities
    ){
        return  Optional.ofNullable(orderDetailServiceEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailServiceMappers::toOrderDetailServiceDTO)
                .collect(Collectors.toList());
    }

    private static List<CouponDTO> convertFromEntityToDtoByCouponEntities(
            List<CouponEntity> couponEntities
    ) {
        return Optional.ofNullable(couponEntities)
                .orElse(Collections.emptyList()).stream()
                .map(CouponMappers::toCouponsDTO)
                .collect(Collectors.toList());
    }

    public static List<OrderDeductionDTO> toOrderDeductionDTOToEntityByOrderDeductionEntities(
            List<OrderDeductionEntity> orderDeductionEntities
    ) {
        return Optional.ofNullable(orderDeductionEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDeductionMappers::toOrderDeductionDTO)
                .collect(Collectors.toList());
    }

    private static ElectronicBillDTO convertFromToDtoByElectronicBillEntities(
            ElectronicBillEntity electronicBillEntity
    ){
        return ElectronicBillMappers.toElectronicBillDTO(electronicBillEntity);
    }

    public static List<OrderDetailDTO> toOrderDetailDTOs(
            List<OrderDetailEntity> orderDetailEntities
    ) {
        
        return orderDetailEntities.stream()
                .map(OrderDetailMappers::getOrderDetailDTO).collect(Collectors.toList());
    }

    private static OrderDetailDTO getOrderDetailDTO(OrderDetailEntity orderDetailEntity) {

        final OrderDetailUserDTO orderUser = OrderDetailUserMappers.toOrderDetailUserMappers(
                orderDetailEntity.getUser()
        );

        return new OrderDetailDTO(
                convertFromEntityToDtoByOrderFiscalIdentifierEntity(
                        orderDetailEntity.getBuyer()
                ),
                orderDetailEntity.getBrandId(),
                orderDetailEntity.getBrandName(),
                orderDetailEntity.getCo2Total(),
                orderDetailEntity.getCurrency(),
                convertFromEntityToDtoByCouponEntities(
                        orderDetailEntity.getCoupons()
                ),
                orderDetailEntity.getId(),
                orderDetailEntity.getDiscount(),
                convertFromEntityToDtoByOrderDetailDiscountEntities(
                        orderDetailEntity.getDiscounts()
                ),
                orderDetailEntity.getDeliveryTypeId(),
                orderDetailEntity.getDeduction(),
                convertFromToDtoByElectronicBillEntities(
                        orderDetailEntity.getElectronicBill()
                ),
                orderDetailEntity.getFlowId(),
                orderDetailEntity.getFoodCoins(),
                orderDetailEntity.getInvoice(),
                orderDetailEntity.getNotes(),
                orderDetailEntity.getOriginId(),
                orderDetailEntity.getOriginName(),
                orderDetailEntity.getOrderNumber(),
                orderDetailEntity.getOrderUuid(),
                orderDetailEntity.getOrderIntegrationId(),
                orderDetailEntity.getOrderIntegrationUser(),
                orderDetailEntity.getOrderIsIntegration(),
                orderDetailEntity.getOrderIntegrationCode(),
                orderDetailEntity.getOperationDate(),
                orderDetailEntity.getOperationTime(),
                convertFromEntityToDtoByOrderDetailPaymentMethodEntities(
                        orderDetailEntity.getPaymentMethods()
                ),
                orderDetailEntity.getPosId(),
                orderDetailEntity.getPosResolutionPrefix(),
                orderDetailEntity.getPrinted(),
                convertFromEntityToDtoByOrderDetailProductEntities(
                        orderDetailEntity.getProducts()
                ),
                convertFromEntityToDtoByOrderDetailServicesEntities(
                        orderDetailEntity.getServices()
                ),
                orderDetailEntity.getStatusId(),
                orderDetailEntity.getStatusName(),
                orderDetailEntity.getStoreId(),
                orderDetailEntity.getStoreName(),
                orderDetailEntity.getSubtotal(),
                orderDetailEntity.getTax(),
                orderDetailEntity.getTotal(),
                orderDetailEntity.getTransactionId(),
                orderDetailEntity.getTransactionUuid(),
                orderDetailEntity.getUid(),
                orderUser,
                Collections.singletonList(orderUser)
        );
    }
}
