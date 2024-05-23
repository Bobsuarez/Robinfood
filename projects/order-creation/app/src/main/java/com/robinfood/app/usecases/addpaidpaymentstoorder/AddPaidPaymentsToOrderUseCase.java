package com.robinfood.app.usecases.addpaidpaymentstoorder;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AddPaidPaymentsToOrderUseCase implements IAddPaidPaymentsToOrderUseCase {

    private static final int ROUNDING = 4;

    // Validate if this use case will not be used later
    @Override
    public void invoke(TransactionRequestDTO transactionRequest) {

        BigDecimal totalPaid = transactionRequest.getPaymentsPaid()
            .stream().map(PaymentMethodDTO::getValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<OrderDTO> orderDTOList = transactionRequest.getOrders()
            .stream()
            .map(item -> paidDistribution(item, totalPaid, transactionRequest.getTotal()))
            .collect(Collectors.toList());

        transactionRequest.setTotal(totalPaid.add(transactionRequest.getTotal()));

        transactionRequest.setOrders(orderDTOList);

    }

    private OrderDTO paidDistribution(OrderDTO orderDTO, BigDecimal totalPaid, BigDecimal totalTransaction) {

        final BigDecimal calculateDiscount = orderDTO.getTotal()
            .multiply(totalPaid)
            .divide(totalTransaction, ROUNDING, RoundingMode.FLOOR);

        orderDTO.setTotal(calculateDiscount.add(orderDTO.getTotal()));

        return orderDTO;
    }
}
