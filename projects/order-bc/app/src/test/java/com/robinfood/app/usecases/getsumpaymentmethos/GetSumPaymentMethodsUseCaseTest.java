package com.robinfood.app.usecases.getsumpaymentmethos;

import com.robinfood.core.dtos.request.transaction.RequestPaymentMethodDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetSumPaymentMethodsUseCaseTest {

    private final List<RequestPaymentMethodDTO> requestPaymentMethodDTOS = new ArrayList<>(Arrays.asList(
            new RequestPaymentMethodDTO(
                    null,
                    1L,
                    1L,
                    3000.0
            ),
            new RequestPaymentMethodDTO(
                    null,
                    1L,
                    1L,
                    3000.0
            )
    ));



    @InjectMocks
    private GetSumPaymentMethodsUseCase sumPaymentMethods;

    @Test
    void test_SumDiscountPrice_When_Pass_Correct_Data() {
        final Double result = sumPaymentMethods.invoke(requestPaymentMethodDTOS);
        Assertions.assertEquals(6000.0, result);
    }
}
