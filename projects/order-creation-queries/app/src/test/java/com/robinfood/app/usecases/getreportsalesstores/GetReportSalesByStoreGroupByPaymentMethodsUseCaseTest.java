package com.robinfood.app.usecases.getreportsalesstores;

import com.robinfood.app.mocks.GetSalesStoresDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.configuration.store.IStoreRepository;
import com.robinfood.repository.paymentmethods.IPaymentMethodsRepository;
import com.robinfood.repository.salesstore.ISalesStoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetReportSalesByStoreGroupByPaymentMethodsUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IPaymentMethodsRepository paymentMethodsRepository;

    @Mock
    private ISalesStoreRepository salesStoreRepository;

    @Mock
    private IStoreRepository storeRepository;

    @InjectMocks
    private GetReportSalesStoreGroupByPaymentMethodsUseCase getReportSalesByStoreGroupByPaymentMethodsUseCase;

    @Test
    void Test_invoke_Should_Return_OK_When_InvokeTheUseCase() {

        final String token = "token";

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(storeRepository.getStore(
                anyLong(),
                anyString()
        )).thenReturn(new Result.Success<>(
                StoreDTO.builder().id(1L).timezone("America/Bogota").build()
        ));

        when(salesStoreRepository.getSalesByStoreGroupByPaymentMethods(
                any(LocalDateTime.class),
                anyLong(),
                anyString(),
                anyString()
        )).thenReturn(new Result.Success<>(GetSalesStoresDTOMock.getDataDefault()));

        when(paymentMethodsRepository.getDataPaymentMethods(
                anyString()
        )).thenReturn(new Result.Success<>(List.of(PaymentMethodsFilterDTO.builder()
                        .id(1L)
                        .name("Cash")
                .build())));

        getReportSalesByStoreGroupByPaymentMethodsUseCase.invoke(LocalDateTime.now(),164L);

        verify(salesStoreRepository).getSalesByStoreGroupByPaymentMethods(
                        any(LocalDateTime.class),
                        anyLong(),
                        anyString(),
                        anyString()
                );

        verify(storeRepository).getStore(
                        anyLong(),
                        anyString()
                );

        verify(paymentMethodsRepository).getDataPaymentMethods(
                        anyString()
                );
    }
}
