package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.orderdetailplushistory.OrderDetailPlusHistoryDTO;
import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.CODE_ORDER_CANCELED;
import static com.robinfood.core.constants.GlobalConstants.CODE_ORDER_PAID;
import static com.robinfood.core.constants.GlobalConstants.ID_ORDER_CANCELED;
import static com.robinfood.core.constants.GlobalConstants.ID_ORDER_DELETED;
import static com.robinfood.core.constants.GlobalConstants.NUMBER_OF_DECIMALS;

public final class OrderDetailPlusHistoryMappers {

    private OrderDetailPlusHistoryMappers()  {
        throw new IllegalStateException("Utility class");
    }

    public static OrderDetailPlusHistoryDTO orderDetailDTOToOrderDetailPlusHistoryDTO(
            OrderDetailDTO orderDetailDTO,
            List<StatusOrderHistoryDTO> statusOrderHistoryList,
            List<PaymentMethodsFilterDTO> paymentMethodsFilterList
    ){

        String statusCode = CODE_ORDER_PAID;

        if(orderDetailDTO.getStatusId() == ID_ORDER_CANCELED ||  orderDetailDTO.getStatusId() == ID_ORDER_DELETED) {
            statusCode = CODE_ORDER_CANCELED;
        }

        BigDecimal total = BigDecimal.valueOf(orderDetailDTO.getTotal());

        return OrderDetailPlusHistoryDTO.builder()
                .brandName(orderDetailDTO.getBrandName())
                .co2Total(orderDetailDTO.getCo2Total())
                .coupons(orderDetailDTO.getCoupons())
                .discount(orderDetailDTO.getDiscount())
                .invoice(orderDetailDTO.getInvoice())
                .originName(orderDetailDTO.getOriginName())
                .orderHistory(statusOrderHistoryList)
                .orderIntegrationId(orderDetailDTO.getOrderIntegrationId())
                .orderIsIntegration(orderDetailDTO.getOrderIsIntegration())
                .operationDate(orderDetailDTO.getOperationDate())
                .operationTime(orderDetailDTO.getOperationTime())
                .paymentMethods(
                        OrderDetailPaymentMethodSummaryMappers
                                .orderDetailPaymentMethodDTOListToOrderDetailPaymentMethodSummaryDTOList(
                                        orderDetailDTO.getPaymentMethods(),
                                        paymentMethodsFilterList
                                )
                )
                .posResolutionPrefix(orderDetailDTO.getPosResolutionPrefix())
                .products(orderDetailDTO.getProducts())
                .services(OrderServicesDetailsSummaryMappers
                        .orderServicesDetailsDTOListToOrderServicesDetailsSummaryDTO(orderDetailDTO.getServices()))
                .statusCode(statusCode)
                .storeName(orderDetailDTO.getStoreName())
                .subtotal(orderDetailDTO.getSubtotal())
                .tax(orderDetailDTO.getTax())
                .total(orderDetailDTO.getTotal())
                .totalPlusCo2(total.add(orderDetailDTO.getCo2Total()).setScale(NUMBER_OF_DECIMALS, RoundingMode.DOWN))
                .user(orderDetailDTO.getUser())
                .build();
    }
}
