package com.robinfood.app.usecases.getlistpaymentSegment;

import com.robinfood.app.mappers.salesbysegment.GetDataGenericSegmentDTOMappers;
import com.robinfood.app.mappers.salesbysegment.GetOrdersSalesDTOMappers;
import com.robinfood.app.mappers.salesbysegment.GetPaymentMethodSaleDTOMappers;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdsUseCase;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.report.salebysegment.DataGenericSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.PaymentMethodSegmentDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.utilities.OrdersPaymentUtil;
import com.robinfood.core.utilities.OrdersUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GetListPaymentMethodsSegmentUseCase implements IGetListPaymentMethodsSegmentUseCase {

    private final IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;

    public GetListPaymentMethodsSegmentUseCase(
            IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase
    ) {
        this.getOrderPaymentByOrderIdsUseCase = getOrderPaymentByOrderIdsUseCase;
    }

    @Override
    public List<PaymentMethodSegmentDTO> getListPaymentMethodsSegment(
            List<OrderEntity> listOrdersCurrent,
            List<OrderEntity> listOrdersLastWeek,
            List<Long> idsToFilter
    ) {

        List<PaymentMethodSegmentDTO> paymentMethodSegmentDTOS = new ArrayList<>();

        List<OrderPaymentDTO> ordersPaymentCurrent = getOrderPaymentByOrderIdsUseCase
                .invoke(OrdersUtil.getIdsOrders(listOrdersCurrent));

        List<OrderPaymentDTO> ordersPaymentLastWeek = getOrderPaymentByOrderIdsUseCase
                .invoke(OrdersUtil.getIdsOrders(listOrdersLastWeek));

        ordersPaymentCurrent = removeIds(ordersPaymentCurrent, idsToFilter);
        ordersPaymentLastWeek = removeIds(ordersPaymentLastWeek, idsToFilter);

        Map<Long, List<OrderPaymentDTO>> groupedListCurrent = OrdersUtil
                .groupedMultipleByEntityAttribute(ordersPaymentCurrent, OrderPaymentDTO::getPaymentMethodId);

        Map<Long, List<OrderPaymentDTO>> groupedListLastWeek = OrdersUtil
                .groupedMultipleByEntityAttribute(ordersPaymentLastWeek, OrderPaymentDTO::getPaymentMethodId);

        List<Long> paymentIds = OrdersUtil
                .findAndGroupSegmentIds(groupedListCurrent, groupedListLastWeek);

        log.info("paymentId : {}", paymentIds);

        for (Long paymentId : paymentIds) {

            BigDecimal getTotalCurrent = OrdersPaymentUtil.sumTotalOrdersPayment(groupedListCurrent, paymentId);
            BigDecimal getTotalLastWeek = OrdersPaymentUtil.sumTotalOrdersPayment(groupedListLastWeek, paymentId);

            DataGenericSegmentDTO dtoWithValueCurrent = GetDataGenericSegmentDTOMappers
                    .valueSaleToDataGenericSegmentDTO(getTotalCurrent);

            DataGenericSegmentDTO dtoWithValueLastWeek = GetDataGenericSegmentDTOMappers
                    .valueSaleToDataGenericSegmentDTO(getTotalLastWeek);

            PaymentMethodSegmentDTO getBrand = GetPaymentMethodSaleDTOMappers.OrderSalesDTOToPaymentMethodSegmentDTO(
                    paymentId,
                    GetOrdersSalesDTOMappers
                            .valueCurrentAndLastWeekToOrderSalesSegmentDTO(dtoWithValueCurrent, dtoWithValueLastWeek)
            );
            paymentMethodSegmentDTOS.add(getBrand);
        }

        return paymentMethodSegmentDTOS;
    }

    @Override
    public List<OrderEntity> filterAndRemoverOrders(List<OrderEntity> dataListOrders, List<Long> idsToFilter) {

        if (Objects.isNull(idsToFilter)) {
            return dataListOrders;
        }

        List<OrderPaymentDTO> ordersPayment = getOrderPaymentByOrderIdsUseCase
                .invoke(OrdersUtil.getIdsOrders(dataListOrders));

        List<OrderPaymentDTO> newListFilterOrderPayment = idsToFilter.stream()
                .flatMap(idFilterPayment ->
                        ordersPayment
                                .stream().filter(orderPaymentDTO ->
                                        Objects.equals(orderPaymentDTO.getPaymentMethodId(), idFilterPayment)))
                .collect(Collectors.toList());

        return newListFilterOrderPayment.stream()
                .flatMap(orderOrderPaymentDTO ->
                        dataListOrders
                                .stream().filter(orderEntity ->
                                        Objects.equals(orderEntity.getId(), orderOrderPaymentDTO.getOrderId())))
                .collect(Collectors.toList());
    }

    private List<OrderPaymentDTO> removeIds(List<OrderPaymentDTO> ordersPaymentCurrent, List<Long> idsToFilters) {

        if (Objects.isNull(idsToFilters)) {
            return ordersPaymentCurrent;
        }

        return idsToFilters.stream()
                .flatMap(idsToFilter -> ordersPaymentCurrent
                        .stream()
                        .filter(paymentDTO -> paymentDTO.getPaymentMethodId().equals(idsToFilter))
                ).collect(Collectors.toList());
    }
}
