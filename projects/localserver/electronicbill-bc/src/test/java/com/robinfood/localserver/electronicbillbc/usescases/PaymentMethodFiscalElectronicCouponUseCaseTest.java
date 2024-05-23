package com.robinfood.localserver.electronicbillbc.usescases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.localserver.commons.dtos.orders.billing.TreasuryPaymentsDTO;
import com.robinfood.localserver.electronicbillbc.mocks.dto.TreasuryPaymentsDTOMocks;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentMethodFiscalElectronicCouponUseCaseTest {

    @InjectMocks
    private PaymentMethodFiscalElectronicCouponUseCase paymentMethodFiscalElectronicCouponUseCase;

    @Test
    void test_Create_Payment_Method_When_Object_Content() throws JsonProcessingException {

        final TreasuryPaymentsDTOMocks treasuryPaymentsDTO = new TreasuryPaymentsDTOMocks();
        final List<TreasuryPaymentsDTO> listTreasuryPaymentsDTO = new ArrayList<>();

        listTreasuryPaymentsDTO.add(treasuryPaymentsDTO.getDefaultTreasuryPaymentsDTOCashData());

        final List<Map<String, String>> payMethod = paymentMethodFiscalElectronicCouponUseCase.invoke(
                listTreasuryPaymentsDTO, BigDecimal.ZERO, BigDecimal.valueOf(16.9));

        final Map<String, String> payMethodMock = new TreeMap<>();
        payMethodMock.put("cMP", "01");
        payMethodMock.put("vMP", "16.90");

        final List<Map<String, String>> payMethodMockList = new ArrayList<>();
        payMethodMockList.add(payMethodMock);

        Assertions.assertEquals(payMethodMockList, payMethod);
    }

    @Test
    void test_Payments_Methods_When_Are_Two_Or_More() throws JsonProcessingException {

        final TreasuryPaymentsDTOMocks treasuryPaymentsDTO = new TreasuryPaymentsDTOMocks();
        final List<TreasuryPaymentsDTO> listTreasuryPaymentsDTO =
                treasuryPaymentsDTO.getDefaultTwoOrMoreTreasuryPaymentsDTOWithDeduction();

        final List<Map<String, String>> paymentMethod = paymentMethodFiscalElectronicCouponUseCase.invoke(
                listTreasuryPaymentsDTO, BigDecimal.valueOf(2), BigDecimal.valueOf(25.90));

        final List<Map<String, String>> paymentMethodMockList = List.of(
                Map.of("cMP", "01",
                        "vMP", "14.39"),
                Map.of("cMP", "03",
                        "vMP", "0.00"),
                Map.of("cMP", "04",
                        "vMP", "9.51")
        );

        Assertions.assertEquals(paymentMethodMockList, paymentMethod);
    }

    @Test
    void test_Create_Pay_Method_When_Object_Empty() {

        final List<TreasuryPaymentsDTO> listTreasuryPaymentsDTO = new ArrayList<>();

        final List<Map<String, String>> payMethod = paymentMethodFiscalElectronicCouponUseCase.invoke(
                listTreasuryPaymentsDTO, BigDecimal.ZERO, BigDecimal.valueOf(16.9));

        final List<Map<String, String>> payMethodMockList = new ArrayList<>();

        Assertions.assertEquals(payMethodMockList, payMethod);
    }

    @Test
    void test_Create_Pay_Method_When_Object_Null() {

        Assertions.assertThrows(NullPointerException.class, () ->
                paymentMethodFiscalElectronicCouponUseCase.invoke(null, BigDecimal.ZERO, BigDecimal.ZERO)
        );
    }
}
