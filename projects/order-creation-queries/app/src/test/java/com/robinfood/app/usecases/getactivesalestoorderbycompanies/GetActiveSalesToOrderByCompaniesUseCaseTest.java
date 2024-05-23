package com.robinfood.app.usecases.getactivesalestoorderbycompanies;

import com.robinfood.app.mocks.ResponseOrderActiveSalesDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.ordersalesbycompanies.IOrderSalesByCompaniesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetActiveSalesToOrderByCompaniesUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IOrderSalesByCompaniesRepository orderSalesByCompaniesRepository;

    @InjectMocks
    private GetActiveSalesToOrderByCompaniesUseCase getActiveSalesToOrderByCompaniesUseCase;

    @Test
    void Test_invoke_Should_Return_When_InvokeTheUseCase() {
        final String token = "token";
        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );
        when(orderSalesByCompaniesRepository.getSalesToOrderByCompanies(
                anyList(),
                anyString(),
                anyList(),
                anyString()
        )).thenReturn(new Result.Success<>(ResponseOrderActiveSalesDTOMock.getDataDefault()));

        getActiveSalesToOrderByCompaniesUseCase.invoke(List.of(1), "2023/03/14 11:00:00", List.of("America/Bogota"));

        verify(orderSalesByCompaniesRepository)
                .getSalesToOrderByCompanies(
                        anyList(),
                        anyString(),
                        anyList(),
                        anyString()
                );
    }

    @Test
    void Test_invoke_Should_Return_Error_When_InvokeTheUseCase() {
        final String token = "token";
        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );
        when(orderSalesByCompaniesRepository.getSalesToOrderByCompanies(
                anyList(),
                anyString(),
                anyList(),
                anyString()
        ))
                .thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.INTERNAL_SERVER_ERROR));

        getActiveSalesToOrderByCompaniesUseCase.invoke(List.of(1), "2023/03/14 11:00:00", List.of("America/Bogota"));

        verify(orderSalesByCompaniesRepository)
                .getSalesToOrderByCompanies(
                        anyList(),
                        anyString(),
                        anyList(),
                        anyString()
                );
    }
}
