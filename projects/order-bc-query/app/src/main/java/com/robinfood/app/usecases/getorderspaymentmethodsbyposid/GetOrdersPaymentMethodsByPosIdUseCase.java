package com.robinfood.app.usecases.getorderspaymentmethodsbyposid;

import com.robinfood.app.mappers.GetOrderPaymentMethodsMapper;
import com.robinfood.core.dtos.GetOrderPaymentMethodsDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.entities.OrderPaymentEntity;
import com.robinfood.core.entities.PaymentMethodEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.core.utilities.CalculateDateDisplacementUTCUtil;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import com.robinfood.repository.orders.IOrdersRepository;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_START;
import static com.robinfood.core.constants.GlobalConstants.ORDER_PAID;
import static com.robinfood.core.constants.GlobalConstants.ORDER_STATUS_CANCELLED;

@Service
@Slf4j
public class GetOrdersPaymentMethodsByPosIdUseCase implements IGetOrdersPaymentMethodsByPosIdUseCase {

    private final IOrderPaymentRepository orderPaymentRepository;
    private final IOrdersRepository ordersRepository;
    private final IPaymentMethodRepository paymentMethodRepository;


    public GetOrdersPaymentMethodsByPosIdUseCase(
            IOrderPaymentRepository orderPaymentRepository,
            IOrdersRepository ordersRepository,
            IPaymentMethodRepository paymentMethodRepository
    ) {

        this.orderPaymentRepository = orderPaymentRepository;
        this.ordersRepository = ordersRepository;
        this.paymentMethodRepository = paymentMethodRepository;

    }

    @Override
    public List<GetOrderPaymentMethodsDTO> invoke(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long posId,
            String timeZone
    ) {

        List<OrderPaymentEntity> orderPayments =
                this.findAllOrderPayments(localDateStart, localDateEnd, posId, timeZone);

        return this.buildOrderPaymentMethods(orderPayments);
    }

    private List<GetOrderPaymentMethodsDTO> buildOrderPaymentMethods(List<OrderPaymentEntity> orderPayments) {

        final List<Long> paymentMethodsIds = orderPayments
                .stream().map(OrderPaymentEntity::getPaymentMethodId).distinct().collect(Collectors.toList());

        List<GetOrderPaymentMethodsDTO> ordersPaymentMethods = new ArrayList<>();

        paymentMethodsIds.forEach((Long paymentMethodId) ->
                ordersPaymentMethods.add(this.validatePaymentMethod(orderPayments,
                        paymentMethodId)));

        return ordersPaymentMethods;

    }

    private List<OrderPaymentEntity> findAllOrderPayments(
            LocalDate localDateStart,
            LocalDate localDateEnd,
            Long posId,
            String timeZone
    ) {

        final Map<String, LocalDateTime> localDateTime =
                CalculateDateDisplacementUTCUtil.getLocalDateByRange(localDateEnd, localDateStart, timeZone);

        List<OrderEntity> orderDailySales = this.ordersRepository
                .findByCreatedAtBetweenAndPaidAndPosIdAndStatusIdNot(
                        localDateTime.get(LOCAL_DATE_START),
                        localDateTime.get(LOCAL_DATE_END),
                        ORDER_PAID,
                        posId,
                        ORDER_STATUS_CANCELLED
                ).orElseThrow(() -> new GenericOrderBcException("order daily sales not found"));

        List<Long> orderIds = orderDailySales
                .stream()
                .map(OrderEntity::getId)
                .collect(Collectors.toList());

        return this.orderPaymentRepository.findOrderPaymentEntitiesByOrderIdIn(orderIds);
    }

    private GetOrderPaymentMethodsDTO validatePaymentMethod(
            List<OrderPaymentEntity> orderPayments,
            Long paymentMethodId
    ) {

        final Long transactions = orderPayments
                .stream()
                .filter(orderPayment -> orderPayment.getPaymentMethodId().equals(paymentMethodId)).count();

        final Double value = orderPayments
                .stream()
                .filter(orderPayment -> orderPayment.getPaymentMethodId().equals(paymentMethodId))
                .mapToDouble(OrderPaymentEntity::getValue).sum();

        PaymentMethodEntity paymentMethod =
                this.paymentMethodRepository
                        .findById(paymentMethodId)
                        .orElseThrow(() -> new GenericOrderBcException("paymentMethods not found"));

        return GetOrderPaymentMethodsMapper.buildGetOrderPaymentMethodsDTO(
                paymentMethod,
                transactions,
                value
        );

    }
}
