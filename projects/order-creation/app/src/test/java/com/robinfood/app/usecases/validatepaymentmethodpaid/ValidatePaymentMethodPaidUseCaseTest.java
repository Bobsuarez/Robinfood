package com.robinfood.app.usecases.validatepaymentmethodpaid;

import com.robinfood.app.mocks.PaymentMethodDTOMock;
import com.robinfood.app.mocks.PaymentMethodPaidResponseDTOMock;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.mocks.TransactionResponseDTOMock;
import com.robinfood.app.util.PaymentMethodsUtil;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.repository.paymentmethods.IPaymentMethodPaidRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class ValidatePaymentMethodPaidUseCaseTest {

    @Mock
    private IPaymentMethodPaidRepository mockPaymentMethodRepository;

    @Mock
    private PaymentMethodsUtil paymentMethodsUtil;

    @InjectMocks
    private ValidatePaymentMethodPaidUseCase validatePaymentMethodPaidUseCase;

    private final static String token = "token";
    private final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    private final List<Long> getIDsByCountryID = List.of(6L);
    private final Map<Long, Long> paymentMethodsIds = new HashMap<>();
    private final Map<Long, Map<Long, Long>> paymentMethodsIdsByCountryId = new HashMap<>();

    @BeforeEach
    void setUp() {
        paymentMethodsIds.put(6L, 1L);
        paymentMethodsIdsByCountryId.put(1L, paymentMethodsIds);
        ReflectionTestUtils.setField(paymentMethodsUtil, "paymentMethodIds", paymentMethodsIdsByCountryId);
    }

    @Test
    void test_Payment_Method_Paid_Happy_Path() {

        transactionRequestDTO.getOrders().get(0).setTotalDiscount(BigDecimal.valueOf(500.0));
        transactionRequestDTO.getOrders().get(0).setSubtotal(BigDecimal.valueOf(8800.0));
        transactionRequestDTO.getOrders().get(0).setTotalTaxes(BigDecimal.valueOf(100.0));
        transactionRequestDTO.getOrders().get(0).setTotalTaxes(BigDecimal.valueOf(8900.0));
        transactionRequestDTO.setUuid(UUID.fromString("7f39541a-8a62-4c73-bb58-6c8449881c74"));

        transactionRequestDTO.setId(1L);
        transactionRequestDTO.setPaymentMethods(PaymentMethodDTOMock.ListWithDetail());
        transactionRequestDTO.setPaid(Boolean.FALSE);

        lenient().when(paymentMethodsUtil.getIDsByCountryID(anyLong())).thenReturn(getIDsByCountryID);
        lenient().when(paymentMethodsUtil.getByCountry(anyLong())).thenReturn(paymentMethodsIds);

        lenient().when(mockPaymentMethodRepository.send(anyString(), any()))
                .thenReturn(CompletableFuture.completedFuture(PaymentMethodPaidResponseDTOMock.build()));

        SaveDataInMemoryUtil.setData(
                transactionRequestDTO.getUuid().toString(),
                new TransactionResponseDTOMock().transactionCreationResponseDTOWithMultiplesOrders
        );

        validatePaymentMethodPaidUseCase.invoke(
                token,
                transactionRequestDTO
        );
    }

    @Test
    void test_Payment_Method_Paid_Null_Happy_Path() {

        transactionRequestDTO.setPaymentMethods(PaymentMethodDTOMock.ListWithDetail());

        validatePaymentMethodPaidUseCase.invoke(
                token,
                transactionRequestDTO
        );
    }

    @Test
    void test_Payment_Method_Paid_STATUS_TRACE_Failure() {

        transactionRequestDTO.getOrders().get(0).setTotalDiscount(BigDecimal.valueOf(500.0));
        transactionRequestDTO.getOrders().get(0).setSubtotal(BigDecimal.valueOf(8800.0));
        transactionRequestDTO.getOrders().get(0).setTotalTaxes(BigDecimal.valueOf(100.0));
        transactionRequestDTO.getOrders().get(0).setTotalTaxes(BigDecimal.valueOf(8900.0));
        transactionRequestDTO.setUuid(UUID.fromString("7f39541a-8a62-4c73-bb58-6c8449881c74"));

        transactionRequestDTO.setId(1L);
        transactionRequestDTO.setPaymentMethods(PaymentMethodDTOMock.ListWithDetail());
        transactionRequestDTO.setPaid(Boolean.FALSE);

        lenient().when(paymentMethodsUtil.getIDsByCountryID(anyLong())).thenReturn(getIDsByCountryID);
        lenient().when(paymentMethodsUtil.getByCountry(anyLong())).thenReturn(paymentMethodsIds);

        lenient().when(mockPaymentMethodRepository.send(anyString(), any()))
                .thenReturn(CompletableFuture.completedFuture(PaymentMethodPaidResponseDTOMock.withStatusTraceNameValueRejected()));

        assertThrows(
                TransactionCreationException.class,
                () -> validatePaymentMethodPaidUseCase.invoke(
                        token,
                        transactionRequestDTO
                )
        );
    }

    @Test
    void test_Validation_Params_Are_Null() {
        assertThrows(
                NullPointerException.class,
                () -> validatePaymentMethodPaidUseCase.invoke(null, null)
        );
    }
}
