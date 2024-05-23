package com.robinfood.app.usecases.getlistpaymentsegment;

import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.report.salebysegment.PaymentMethodSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.ItemDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.OrdersDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.PaymentMethodsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.PercentageSalesDifferenceDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.TotalDTOResponse;
import com.robinfood.core.mappers.report.salesegment.ItemResponseMappers;
import com.robinfood.core.mappers.report.salesegment.OrdersSegmentToOrdersResponseMappers;
import com.robinfood.core.mappers.report.salesegment.PercentageToResponseMappers;
import com.robinfood.core.mappers.report.salesegment.TotalDTOResponseMappers;
import com.robinfood.core.mappers.report.salesegment.ValueAWeekBeforeToResponseMappers;
import com.robinfood.core.mappers.report.salesegment.ValueCurrentToResponseMappers;
import com.robinfood.core.utilities.CalculatorPercentageUtil;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_FALSE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE;
import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

@Service
public class GetPaymentBySegmentUseCase implements IGetPaymentBySegmentUseCase {

    @Override
    public PaymentMethodsDTOResponse invoke(
            String currencyType,
            List<PaymentMethodSegmentDTO> paymentMethodSegmentDTOS,
            List<PaymentMethodsFilterDTO> paymentDTOList) {

        return PaymentMethodsDTOResponse.builder()
                .items(toBuildListItems(currencyType, paymentMethodSegmentDTOS, paymentDTOList))
                .total(toBuildTotal(currencyType, paymentMethodSegmentDTOS))
                .build();
    }

    private List<ItemDTOResponse> toBuildListItems(
            String currencyType,
            List<PaymentMethodSegmentDTO> paymentMethodSegmentDTOS,
            List<PaymentMethodsFilterDTO> paymentDTOList
    ) {
        return paymentMethodSegmentDTOS.stream()
                .map((PaymentMethodSegmentDTO payment) -> {

                    final String namePayment = getPaymentMethodsName(payment.getId(), paymentDTOList);
                    final OrdersDTOResponse orders = OrdersSegmentToOrdersResponseMappers
                            .ordersResponse(payment.getOrders());

                    return ItemResponseMappers
                            .itemResponseMappers(currencyType, payment.getId(), namePayment, orders);

                }).collect(Collectors.toList());
    }

    private TotalDTOResponse toBuildTotal(
            String currencyType,
            List<PaymentMethodSegmentDTO> paymentMethodSegmentDTOS
    ) {

        final BigDecimal sumResultSalesCurrent = sumValueSales(DEFAULT_BOOLEAN_FALSE_VALUE, paymentMethodSegmentDTOS);
        final BigDecimal sumResultSaleAWeekBefore = sumValueSales(DEFAULT_BOOLEAN_TRUE_VALUE, paymentMethodSegmentDTOS);

        return TotalDTOResponseMappers.totalToResponse(
                currencyType,
                percentageValueSalesCurrent(sumResultSaleAWeekBefore, sumResultSalesCurrent),
                ValueAWeekBeforeToResponseMappers.valueAssignment(sumResultSaleAWeekBefore),
                ValueCurrentToResponseMappers.valueAssignment(sumResultSalesCurrent)
        );
    }

    @NotNull
    private BigDecimal sumValueSales(
            boolean isSalesAWeekBefore,
            List<PaymentMethodSegmentDTO> paymentMethodSegmentDTOS
    ) {
        // Return sum sales a week before
        if (isSalesAWeekBefore) {
            return paymentMethodSegmentDTOS
                    .stream()
                    .map(convertTotal -> convertTotal.getOrders().getSalesAWeekBefore().getValue())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        // Return sum sales current day
        return paymentMethodSegmentDTOS
                .stream()
                .map(convertTotal -> convertTotal.getOrders().getSalesCurrent().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @NotNull
    private PercentageSalesDifferenceDTOResponse percentageValueSalesCurrent(
            BigDecimal valueAWeekBefore,
            BigDecimal valueCurrent
    ) {
        final BigDecimal result = CalculatorPercentageUtil.getPercentageDifference(valueCurrent, valueAWeekBefore);
        return PercentageToResponseMappers.valueAssignment(result);
    }

    private static String getPaymentMethodsName(Long paymentId, List<PaymentMethodsFilterDTO> paymentDTOList) {

        return Optional.ofNullable(paymentDTOList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(paymentDTO -> paymentDTO.getId().equals(paymentId))
                .map(PaymentMethodsFilterDTO::getName)
                .findFirst()
                .orElse(DEFAULT_STRING_VALUE);
    }
}
