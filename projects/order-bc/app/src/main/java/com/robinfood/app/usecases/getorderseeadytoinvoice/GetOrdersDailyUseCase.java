package com.robinfood.app.usecases.getorderseeadytoinvoice;

import com.robinfood.app.mappers.OrderDailyMapper;
import com.robinfood.app.mappers.OrderMappers;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdsUseCase;
import com.robinfood.app.usecases.getordersnotdirectpayments.IGetOrdersNotDirectPaymentsUseCase;
import com.robinfood.app.usecases.getoriginsbyids.IGetOriginsByIdsUseCase;
import com.robinfood.app.usecases.getuserdatabyorderids.IGetUserDataByOrderIdsUseCase;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.dtos.OrderDailyDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.dtos.OriginDTO;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.utilities.CalculateDateDisplacementUTC;
import com.robinfood.repository.orders.IOrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_DOUBLE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.ORDER_STATUS_CREATED;

/**
 * Implementation of IGetOrdersDailyUseCase
 */
@Service
@Slf4j
public class GetOrdersDailyUseCase implements IGetOrdersDailyUseCase {

    private final IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;

    private final IGetOrdersNotDirectPaymentsUseCase getOrdersNotDirectPaymentsUseCase;
    private final IGetOriginsByIdsUseCase getOriginsByIdsUseCase;
    private final IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase;
    private final IOrdersRepository ordersRepository;

    public GetOrdersDailyUseCase(
            IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase,
            IGetOrdersNotDirectPaymentsUseCase getOrdersNotDirectPaymentsUseCase,
            IGetOriginsByIdsUseCase getOriginsByIdsUseCase,
            IGetUserDataByOrderIdsUseCase getUserDataByOrderIdsUseCase,
            IOrdersRepository ordersRepository
    ) {
        this.getOrderPaymentByOrderIdsUseCase = getOrderPaymentByOrderIdsUseCase;
        this.getOrdersNotDirectPaymentsUseCase = getOrdersNotDirectPaymentsUseCase;
        this.getOriginsByIdsUseCase = getOriginsByIdsUseCase;
        this.getUserDataByOrderIdsUseCase = getUserDataByOrderIdsUseCase;
        this.ordersRepository = ordersRepository;
    }

    public List<OrderDailyDTO> invoke(Long storeId, String timeZone) {

        log.info("Execute Orders Daily UseCase storeId {}, timeZone {}", storeId, timeZone);

        final ZoneId zoneId = CalculateDateDisplacementUTC.getZoneId(timeZone);
        final LocalDate localDate = CalculateDateDisplacementUTC.getLocalDate(zoneId);

        final LocalDateTime localDateTimeStart = CalculateDateDisplacementUTC.getLocalDateTimeStart(localDate, zoneId);
        final LocalDateTime localDateTimeEnd = CalculateDateDisplacementUTC.getLocalDateTimeEnd(localDate, zoneId);

        List<OrderEntity> orderEntityList = this.ordersRepository
                .findAllByStoreIdAndPaidAndFullSynchronizedAndStatusIdAndCreatedAtBetweenOrderByCreatedAtDesc(
                        storeId,
                        DEFAULT_BOOLEAN_FALSE_VALUE,
                        DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT,
                        ORDER_STATUS_CREATED,
                        localDateTimeStart,
                        localDateTimeEnd
                );

        log.info("Result Orders Repository {}", orderEntityList);

        List<Long> ordersIds = orderEntityList.stream().map(OrderEntity::getId).collect(Collectors.toList());

        final List<OrderPaymentDTO> paymentDTOS = this.getOrderPaymentByOrderIdsUseCase.invoke(ordersIds);

        final List<OrderDTO> ordersWithPendingPayment =
                getOrdersWithPendingPaymentMethods(orderEntityList, paymentDTOS).stream()
                        .map(OrderMappers::toOrderDTO)
                        .collect(Collectors.toList());

        final List<OrderDTO> ordersNotDirectPayments = getOrdersNotDirectPaymentsUseCase
                .invoke(ordersWithPendingPayment, paymentDTOS);

        List<Long> originsIds = ordersNotDirectPayments.stream()
                .map(OrderDTO::getOriginId).collect(Collectors.toList())
                .stream().distinct().collect(Collectors.toList());

        List<OriginDTO> originDTOS = this.getOriginsByIdsUseCase.invoke(originsIds);

        log.info("Result Origins By Ids UseCase {}", originDTOS);

        List<UserDataDTO> userDataDTOS = this.getUserDataByOrderIdsUseCase.invoke(ordersIds);

        log.info("Result User Data By Order Ids UseCase {}", userDataDTOS);

        return ordersNotDirectPayments.stream().map(
                order -> OrderDailyMapper
                        .orderEntityToOrderDailyDto(order, originDTOS, userDataDTOS))
                        .collect(Collectors.toList());
    }

    private List<OrderEntity> getOrdersWithPendingPaymentMethods(
            List<OrderEntity> orderEntityList,
            final List<OrderPaymentDTO> orderPaymentDTOS
    ) {
        
        return  orderEntityList.stream().filter(order -> order.getTotal() > orderPaymentDTOS.stream()
                        .filter(orderPaymentDTO -> orderPaymentDTO.getOrderId().equals(order.getId()))
                        .map(OrderPaymentDTO::getValue)
                        .reduce(DEFAULT_DOUBLE_VALUE, Double::sum))
                        .collect(Collectors.toList());
    }

}
