package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.DataElectronicBillingDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.ElectronicBillDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDeductionDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDiscountDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailPaymentMethodDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailUserDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderFiscalIdentifierDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderThirdPartyDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.ElectronicBillEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDeductionEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailDiscountEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailPaymentMethodEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductEntity;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderFiscalIdentifierEntity;
import com.robinfood.orderorlocalserver.utilities.ReplaceCharactersUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.orderorlocalserver.constants.GlobalConstants.DEFAULT_STRING_VALUE;

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

    public static List<OrderDeductionDTO> toOrderDeductionDTOToEntityByOrderDeductionEntities(
            List<OrderDeductionEntity> orderDeductionEntities
    ) {
        return Optional.ofNullable(orderDeductionEntities)
                .orElse(Collections.emptyList()).stream()
                .map(OrderDeductionMappers::toOrderDeductionDTO)
                .collect(Collectors.toList());
    }


    private static ElectronicBillDTO convertElectronicBillEntityToElectronicBillDTO(
            ElectronicBillEntity electronicBillEntity
    ) {
        return Optional.ofNullable(electronicBillEntity)
                .map((ElectronicBillEntity e) -> ElectronicBillDTO.builder()
                        .orderElectronicBilling(
                                GetElectronicBillMapper.dataElectronicBillingEntityToDataElectronicBillingDTO(
                                        e.getOrderElectronicBilling()))
                        .orderThirdParty(
                                GetElectronicBillMapper.orderThirdPartyEntityToOrderThirdPartyDTO(
                                        e.getOrderThirdParty()))
                        .build())
                .orElse(null);
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
                List.of(),
                orderDetailEntity.getId(),
                orderDetailEntity.getDiscount(),
                convertFromEntityToDtoByOrderDetailDiscountDTOS(
                        orderDetailEntity.getDiscounts()
                ),
                orderDetailEntity.getDeliveryTypeId(),
                convertElectronicBillEntityToElectronicBillDTO(
                        orderDetailEntity.getElectronicBill()
                ),
                orderDetailEntity.getDeduction(),
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
                orderDetailEntity.getPrinted(),
                convertFromEntityToDtoOrderDetailProductDTOS(
                        orderDetailEntity.getProducts()
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
            Collection<OrderDetailEntity> orderDetailEntities
    ){

        return orderDetailEntities.stream().map(orderDetailEntity ->
                createOrderDetailDTO(orderDetailEntity, OrderDetailUserMappers.toOrderDetailUserMappers(
                        orderDetailEntity.getUser()
                ))
        ).collect(Collectors.toList());
    }
}
