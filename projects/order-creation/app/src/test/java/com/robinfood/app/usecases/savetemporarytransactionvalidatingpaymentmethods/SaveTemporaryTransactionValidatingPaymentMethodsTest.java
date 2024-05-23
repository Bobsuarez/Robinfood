package com.robinfood.app.usecases.savetemporarytransactionvalidatingpaymentmethods;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.savetemporarytransaction.ISaveTemporaryTransactionUseCase;
import com.robinfood.app.util.PaymentMethodsUtil;
import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.robinfood.core.constants.DataInMemoryConstants.INPUT_REQUEST_TRANSACTION;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class SaveTemporaryTransactionValidatingPaymentMethodsTest {

    final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    @Mock
    private ISaveTemporaryTransactionUseCase saveTemporaryTransactionUseCase;

    @Mock
    private PaymentMethodsUtil paymentMethodsUtil;

    @InjectMocks
    private SaveTemporaryTransactionValidatingPaymentMethods saveTemporaryTransactionValidatingPaymentMethods;

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
    void test_Save_Temporary_Transaction_Success() {

        SaveDataInMemoryUtil.setData(INPUT_REQUEST_TRANSACTION, transactionRequestDTO);
        lenient().when(paymentMethodsUtil.getIDsByCountryID(anyLong())).thenReturn(getIDsByCountryID);
        lenient().when(paymentMethodsUtil.getByCountry(anyLong())).thenReturn(paymentMethodsIds);

        doNothing().when(saveTemporaryTransactionUseCase).invoke(transactionRequestDTO);

        saveTemporaryTransactionValidatingPaymentMethods.invoke(transactionRequestDTO);
    }

    @Test
    void test_Save_Temporary_Transaction_Payment_Empty() {

        lenient().when(paymentMethodsUtil.getIDsByCountryID(anyLong())).thenReturn(null);

        saveTemporaryTransactionValidatingPaymentMethods.invoke(transactionRequestDTO);
    }
}
