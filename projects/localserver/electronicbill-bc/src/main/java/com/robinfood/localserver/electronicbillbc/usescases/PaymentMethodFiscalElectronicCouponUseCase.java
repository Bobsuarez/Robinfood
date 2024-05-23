package com.robinfood.localserver.electronicbillbc.usescases;

import com.robinfood.localserver.commons.dtos.orders.billing.TreasuryPaymentsDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.treasurydepartament.ParameterDTO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.robinfood.localserver.commons.constants.SefazConstants.ROUNDING_SCALE;
import static com.robinfood.localserver.electronicbillbc.configs.constants.FiscalElectronicCouponConstants.TAG_PAY_METHOD_VALUE;

@Service
@Slf4j
public class PaymentMethodFiscalElectronicCouponUseCase implements IPaymentMethodFiscalElectronicCouponUseCase {

    @SneakyThrows
    @Override
    public List<Map<String, String>> invoke(List<TreasuryPaymentsDTO> listTreasuryPaymentsDTO,
                                            BigDecimal totalDeduction, BigDecimal totalOrder) {

        BigDecimal totalDeductionPaymentsMethods = BigDecimal.ZERO;

        for (TreasuryPaymentsDTO treasuryPayment : listTreasuryPaymentsDTO) {

            BigDecimal paymentMethodDeduction = totalDeduction.multiply(treasuryPayment.getValue())
                    .divide(totalOrder, ROUNDING_SCALE, RoundingMode.DOWN);

            totalDeductionPaymentsMethods = totalDeductionPaymentsMethods.add(paymentMethodDeduction);

            treasuryPayment.getParameters().add(
                    ParameterDTO.builder()
                            .name(TAG_PAY_METHOD_VALUE)
                            .value(treasuryPayment.getValue().subtract(paymentMethodDeduction)
                                    .setScale(ROUNDING_SCALE).toString())
                            .build()
            );
        }

        List<Map<String, String>> paymentsMethods =
                listTreasuryPaymentsDTO.stream().map((TreasuryPaymentsDTO treasuryPayment) ->
                                treasuryPayment.getParameters().stream()
                                        .collect(
                                                Collectors.toMap(ParameterDTO::getName, ParameterDTO::getValue)
                                        )
                        )
                        .collect(Collectors.toList());

        BigDecimal differenceAmount = totalDeduction.subtract(totalDeductionPaymentsMethods);

        if (differenceAmount.compareTo(BigDecimal.ZERO) > 0) {
            roundAmount(paymentsMethods, differenceAmount);
        }

        return paymentsMethods;
    }

    private void roundAmount(List<Map<String, String>> paymentsMethods, BigDecimal differenceAmount) {

        paymentsMethods.stream().filter(payMethod -> Double.parseDouble(payMethod.get(TAG_PAY_METHOD_VALUE)) > 0)
                .findFirst()
                .stream()
                .forEach(payMethod ->
                        payMethod.put(TAG_PAY_METHOD_VALUE,
                                BigDecimal.valueOf(Double.parseDouble(payMethod.get(TAG_PAY_METHOD_VALUE)))
                                        .subtract(differenceAmount)
                                        .setScale(ROUNDING_SCALE).toString())
                );
    }
}
