package com.robinfood.app.usecases.orderdetailfilter;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.enums.Result;
import com.robinfood.app.mocks.ResponseOrderFilterDTOFMock;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.orderfilter.IOrderFilterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDetailFilterUseCaseTest {

    @Mock
    private IOrderFilterRepository orderFilterRepository;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private OrderDetailFilterUseCase orderDetailFilterUseCase;

    private final String token = "token";

    @Test
    void Test_invoke_Should_RespondToOrdersAccordingToFilters_When_LetsInvokeTheUseCase() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(
                TokenModel.builder()
                        .accessToken(token)
                        .build()
        );

        when(orderFilterRepository.getOrderFilter(
                anyInt(),
                anyString(),
                any(LocalDate.class),
                any(LocalDate.class),
                anyInt(),
                anyLong(),
                anyString(),
                anyString()
        )).thenReturn(new Result.Success<>(ResponseOrderFilterDTOFMock.getDataDefault()));

        orderDetailFilterUseCase.invoke(
                1,
                "",
                LocalDate.now(),
                LocalDate.now(),
                1,
                1L,
                "America/bogota"
        );

        verify(getTokenBusinessCapabilityUseCase).invoke();

        verify(orderFilterRepository)
                .getOrderFilter(
                        anyInt(),
                        anyString(),
                        any(LocalDate.class),
                        any(LocalDate.class),
                        anyInt(),
                        anyLong(),
                        anyString(),
                        anyString()
                );
    }
}
