package com.robinfood.core.mappers;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

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
import com.robinfood.core.dtos.OrderThirdPartyDTO;
import com.robinfood.core.entities.CouponEntity;
import com.robinfood.core.entities.ElectronicBillEntity;
import com.robinfood.core.entities.OrderDeductionEntity;
import com.robinfood.core.entities.OrderDetailDiscountEntity;
import com.robinfood.core.entities.OrderDetailEntity;
import com.robinfood.core.entities.OrderDetailPaymentMethodEntity;
import com.robinfood.core.entities.OrderDetailProductEntity;
import com.robinfood.core.entities.OrderDetailServiceEntity;
import com.robinfood.core.entities.OrderFiscalIdentifierEntity;
import com.robinfood.core.entities.OrderThirdPartyEntity;
import com.robinfood.core.utilities.ReplaceCharactersUtil;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Mapping detail order for print
 */
public final class OrderDetailPrintMappers {

    private OrderDetailPrintMappers() {
        throw new IllegalStateException("Utility class");
    }

    private static List<OrderDetailDiscountDTO> convertFromEntityToDtoByOrderDetailDiscountDTOS(
            List<OrderDetailDiscountEntity> orderDetailDiscountEntities
    ) {
        return Optional.ofNullable(orderDetailDiscountEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailDiscountMappers::toOrderDetailDiscountDTO)
                .collect(Collectors.toList());
    }

    private static String validateNote(
            String note
    ) {
        if (Objects.isNull(note)) {
            return DEFAULT_STRING_VALUE;
        }

        return ReplaceCharactersUtil.lineBreaksSpecCharacterStringNull(note);
    }

    public static  List<CouponDTO> convertFromEntityToDTOByCouponDTOS(
            List<CouponEntity> couponEntities
    ){

        return Optional.ofNullable(couponEntities)
                .orElse(Collections.emptyList()).stream()
                .map(CouponMappers::toCouponsDTO)
                .collect(Collectors.toList());
    }

    private static List<OrderDetailPaymentMethodDTO> convertFromEntityToDtoByOrderDetailPaymentMethodDTOS(
            List<OrderDetailPaymentMethodEntity> orderDetailPaymentMethodEntities
    ) {
        return Optional.ofNullable(orderDetailPaymentMethodEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailPaymentMethodMappers::toOrderDetailPaymentMethodDTO)
                .collect(Collectors.toList());
    }

    private static OrderFiscalIdentifierDTO convertFromEntityToDtoByOrderFiscalIdentifierDTOS(
            OrderFiscalIdentifierEntity orderFiscalIdentifierEntity
    ) {
        if (Objects.isNull(orderFiscalIdentifierEntity)) {
            return new OrderFiscalIdentifierDTO(
                    DEFAULT_STRING_VALUE
            );
        }

        return OrderFiscalIdentifierMappers.toOrderFiscalIdentifierDTOMappers(orderFiscalIdentifierEntity);
    }

    private static List<OrderDetailProductDTO> convertFromEntityToDtoOrderDetailProductDTOS(
            List<OrderDetailProductEntity> orderDetailProductEntities
    ) {
        return Optional.ofNullable(orderDetailProductEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailProductMappers::toOrderDetailProductDTO)
                .collect(Collectors.toList());
    }

    private static List<OrderDetailServiceDTO> convertFromEntityToDoOrderDetailServicesDTOS(
            List<OrderDetailServiceEntity> orderDetailServiceEntities
    ){
        return Optional.ofNullable(orderDetailServiceEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDetailServiceMappers::toOrderDetailServiceDTO)
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

    private static OrderDetailDTO createOrderDetailDTO(OrderDetailEntity orderDetailEntity,
                                                       OrderDetailUserDTO orderUser) {
        return new OrderDetailDTO(
                convertFromEntityToDtoByOrderFiscalIdentifierDTOS(
                        orderDetailEntity.getBuyer()
                ),
                orderDetailEntity.getBrandId(),
                orderDetailEntity.getBrandName(),
                orderDetailEntity.getCo2Total(),
                orderDetailEntity.getCurrency(),
                convertFromEntityToDTOByCouponDTOS(
                        orderDetailEntity.getCoupons()
                ),
                orderDetailEntity.getId(),
                orderDetailEntity.getDiscount(),
                convertFromEntityToDtoByOrderDetailDiscountDTOS(
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
                validateNote(orderDetailEntity.getNotes()),
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
                convertFromEntityToDtoByOrderDetailPaymentMethodDTOS(
                        orderDetailEntity.getPaymentMethods()
                ),
                orderDetailEntity.getPosId(),
                orderDetailEntity.getPosResolutionPrefix(),
                orderDetailEntity.getPrinted(),
                convertFromEntityToDtoOrderDetailProductDTOS(
                        orderDetailEntity.getProducts()
                ),
                convertFromEntityToDoOrderDetailServicesDTOS(
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

    public static List<OrderDetailDTO> toOrderDetailPrintDTO(
            List<OrderDetailEntity> orderDetailEntities
    ) {

        return orderDetailEntities.stream().map(orderDetailEntity ->
                createOrderDetailDTO(orderDetailEntity, OrderDetailUserMappers.toOrderDetailUserMappers(
                        orderDetailEntity.getUser()
                ))
        ).collect(Collectors.toList());
    }
}
