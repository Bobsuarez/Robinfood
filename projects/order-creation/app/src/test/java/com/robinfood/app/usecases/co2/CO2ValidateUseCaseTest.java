package com.robinfood.app.usecases.co2;

import com.robinfood.app.components.Utils;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.validateco2.ValidateCO2UseCase;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.CO2CalculateResponseEntity;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repository.co2.ICO2Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ConfigurableApplicationContext;
import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CO2ValidateUseCaseTest {

    static {
        try {
            Mockito.mockStatic(Utils.class);
        } catch (Exception e) {}
    }

    private ConfigurableApplicationContext configurableApplicationContext;

    @Mock
    private ICO2Repository co2Repository;

    @InjectMocks
    private ValidateCO2UseCase validateCO2UseCase;

    final String token = "token";

    @Test
    public void test_Invoke_Success_When_CO2ValidationFalse() {
        doReturn("false")
                .when(Utils.class);
        Utils.getApplicationProperty(anyString());

        TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;

        assertAll(() -> validateCO2UseCase.invoke(token, transactionRequest));
    }

    @Test
    public void test_Invoke_Success_When_CO2ValidationNull() {
        doReturn(null)
                .when(Utils.class);
        Utils.getApplicationProperty(anyString());

        TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;

        assertAll(() -> validateCO2UseCase.invoke(token, transactionRequest));
    }

    @Test
    public void test_Invoke_Success_When_CO2OrderValueNull() {
        doReturn("true")
                .when(Utils.class);
        Utils.getApplicationProperty(anyString());

        TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;

        transactionRequest.getOrders().get(0).setCo2Total(null);

        assertAll(() -> validateCO2UseCase.invoke(token, transactionRequest));
    }

    @Test
    public void test_Invoke_Success_When_CO2OrderValueNotNull_And_Equal() {
        doReturn("true")
                .when(Utils.class);
        Utils.getApplicationProperty(anyString());

        TransactionRequestDTO transactionRequest = TransactionRequestDTO.builder()
                .orders(Collections.singletonList(
                    OrderDTO.builder()
                        .co2Total(BigDecimal.ONE)
                        .build()
                    )
                ).build();


        when(co2Repository.calculateCO2Compensation(token, transactionRequest.getOrders().get(0))).thenReturn(
                CO2CalculateResponseEntity.builder()
                        .co2TotalValue(BigDecimal.TEN)
                        .build()
        );

        assertThrows(
            TransactionCreationException.class,
            () -> validateCO2UseCase.invoke(token, transactionRequest)
        );
    }

    @Test
    public void test_Invoke_Success_When_CO2OrderValueZero() {
        doReturn("true")
                .when(Utils.class);
        Utils.getApplicationProperty(anyString());

        TransactionRequestDTO transactionRequest = TransactionRequestDTO.builder()
                .orders(Collections.singletonList(
                                OrderDTO.builder()
                                        .co2Total(BigDecimal.ZERO)
                                        .build()
                        )
                ).build();

        assertAll(() -> validateCO2UseCase.invoke(token, transactionRequest));
    }

    @Test
    public void test_Invoke_Success_When_CO2OrderValueNotNull_And_Different() {
        doReturn("true")
                .when(Utils.class);
        Utils.getApplicationProperty(anyString());

        TransactionRequestDTO transactionRequest = TransactionRequestDTO.builder()
                .orders(Collections.singletonList(
                                OrderDTO.builder()
                                        .co2Total(BigDecimal.ONE)
                                        .build()
                        )
                ).build();

        when(co2Repository.calculateCO2Compensation(token, transactionRequest.getOrders().get(0))).thenReturn(
                CO2CalculateResponseEntity.builder()
                        .co2TotalValue(BigDecimal.ONE)
                        .build()
        );

        assertAll(() -> validateCO2UseCase.invoke(token, transactionRequest));
    }

    @Test
    public void test_Invoke_Error_When_TokenNull() {
        TransactionRequestDTO transactionRequest = TransactionRequestDTO.builder()
                .orders(Collections.singletonList(
                                OrderDTO.builder()
                                        .co2Total(BigDecimal.ONE)
                                        .build()
                        )
                ).build();
        
        assertThrows(
            NullPointerException.class,
            () -> validateCO2UseCase.invoke(null, transactionRequest)
        );
    }

    @Test
    public void test_Invoke_Error_When_TransactionRequestNull() {
        assertThrows(
            NullPointerException.class,
            () -> validateCO2UseCase.invoke(token, null)
        );
    }
}
