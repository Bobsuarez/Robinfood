package com.robinfood.app.usecases.getlistpaymentSegment;

import com.robinfood.core.dtos.report.salebysegment.PaymentMethodSegmentDTO;
import com.robinfood.core.entities.OrderEntity;

import java.util.List;

/**
 * Use case containing payment methods segment logic
 */
public interface IGetListPaymentMethodsSegmentUseCase {

    List<PaymentMethodSegmentDTO> getListPaymentMethodsSegment(
            List<OrderEntity> listOrdersCurrent,
            List<OrderEntity> listOrdersLastWeek,
            List<Long> idsToFilter
    );

    List<OrderEntity> filterAndRemoverOrders(List<OrderEntity> dataListOrders, List<Long> idsToFilter);

}
