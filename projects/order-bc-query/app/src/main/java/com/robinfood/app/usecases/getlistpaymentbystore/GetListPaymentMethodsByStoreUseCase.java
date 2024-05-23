package com.robinfood.app.usecases.getlistpaymentbystore;

import com.robinfood.app.mappers.salesbysegment.GetDataGenericSegmentDTOMappers;
import com.robinfood.app.mappers.salesbystore.GetPaymentMethodsOfStoreDTOMappers;
import com.robinfood.app.mappers.salesbystore.GetValuePaymentMethodsByStoreDTOMappers;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdsUseCase;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.report.salebysegment.DataGenericSegmentDTO;
import com.robinfood.core.dtos.report.salebystore.PaymentMethodsOfStoreDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.utilities.OrdersPaymentUtil;
import com.robinfood.core.utilities.OrdersUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GetListPaymentMethodsByStoreUseCase implements IGetListPaymentMethodsByStoreUseCase {

    private final IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;

    public GetListPaymentMethodsByStoreUseCase(
            IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase
    ) {
        this.getOrderPaymentByOrderIdsUseCase = getOrderPaymentByOrderIdsUseCase;
    }

    @Override
    public List<PaymentMethodsOfStoreDTO> invoke(
            List<OrderEntity> listOrdersCurrent,
            List<OrderEntity> listOrdersLastWeek
    ) {
        List<PaymentMethodsOfStoreDTO> paymentMethodsOfStoreDTOS = new ArrayList<>();

        List<OrderPaymentDTO> ordersPaymentCurrent = getOrderPaymentByOrderIdsUseCase
                .invoke(OrdersUtil.getIdsOrders(listOrdersCurrent));

        List<OrderPaymentDTO> ordersPaymentLastWeek = getOrderPaymentByOrderIdsUseCase
                .invoke(OrdersUtil.getIdsOrders(listOrdersLastWeek));

        Map<Long, List<OrderPaymentDTO>> groupedListCurrent = OrdersUtil
                .groupedMultipleByEntityAttribute(ordersPaymentCurrent, OrderPaymentDTO::getPaymentMethodId);

        Map<Long, List<OrderPaymentDTO>> groupedListLastWeek = OrdersUtil
                .groupedMultipleByEntityAttribute(ordersPaymentLastWeek, OrderPaymentDTO::getPaymentMethodId);

        final List<Long> segmentIds = OrdersUtil.findAndGroupSegmentIds(groupedListCurrent, groupedListLastWeek);

        for (Long paymentId : segmentIds) {

            final BigDecimal getTotalCurrent = OrdersPaymentUtil.sumTotalOrdersPayment(groupedListCurrent, paymentId);
            final BigDecimal getTotalLastWeek = OrdersPaymentUtil.sumTotalOrdersPayment(groupedListLastWeek, paymentId);

            final Long currentOrderCount = OrdersPaymentUtil.sumCounterOrders(groupedListCurrent, paymentId);
            final Long currentOrderLastWeek = OrdersPaymentUtil.sumCounterOrders(groupedListLastWeek, paymentId);

            DataGenericSegmentDTO withValueCurrent = GetDataGenericSegmentDTOMappers
                    .valueSaleToDataGenericSegmentDTO(getTotalCurrent, currentOrderCount);

            DataGenericSegmentDTO withValueLastWeek = GetDataGenericSegmentDTOMappers
                    .valueSaleToDataGenericSegmentDTO(getTotalLastWeek, currentOrderLastWeek);

            PaymentMethodsOfStoreDTO paymentMethodsOfStoreDTO = GetPaymentMethodsOfStoreDTOMappers
                    .CountAndValueByOrdersDTOToPaymentMethodsOfStoreDTO(
                            paymentId,
                            GetValuePaymentMethodsByStoreDTOMappers
                                    .valueCurrentAndLastWeekToCountAndValueByOrdersDTO(
                                            withValueCurrent,
                                            withValueLastWeek
                                    )
                    );
            paymentMethodsOfStoreDTOS.add(paymentMethodsOfStoreDTO);
        }

        return Optional.ofNullable(paymentMethodsOfStoreDTOS)
                .stream().flatMap(Collection::stream)
                .sorted(Comparator.comparing(PaymentMethodsOfStoreDTO::getPaymentMethodId))
                .collect(Collectors.toList());
    }
}
