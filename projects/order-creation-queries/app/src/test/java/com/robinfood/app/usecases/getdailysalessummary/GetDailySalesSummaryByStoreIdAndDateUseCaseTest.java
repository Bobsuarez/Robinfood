package com.robinfood.app.usecases.getdailysalessummary;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.OrderDailySaleSummaryDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.orderdailysalessummary.IOrderDailySalesSummaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetDailySalesSummaryByStoreIdAndDateUseCaseTest {

    @Mock
    private IOrderDailySalesSummaryRepository mockOrderDailySalesSummaryRepository;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private GetDailySalesSummaryByStoreIdAndDateUseCase getDailySalesSummaryByStoreIdAndDateUseCase;

    private final OrderDailySaleSummaryDTO orderDailySaleSummaryDTO = OrderDailySaleSummaryDTO.builder()
        .ordersNumber(10)
        .salesSummary(10.0)
        .build();

    @Test
    void test_getDailySalesSummary_ok() {

        when(getTokenBusinessCapabilityUseCase.invoke())
            .thenReturn(TokenModel.builder()
                .accessToken("token")
                .build());

        when(mockOrderDailySalesSummaryRepository
            .getOrderDailySalesSummary("token", 1L, LocalDate.parse("2022-05-05")))
            .thenReturn(new Result.Success<>(orderDailySaleSummaryDTO));

        final Result<OrderDailySaleSummaryDTO> result =
            getDailySalesSummaryByStoreIdAndDateUseCase.invoke(1L, LocalDate.parse("2022-05-05"));

        assertEquals(orderDailySaleSummaryDTO, ((Result.Success<OrderDailySaleSummaryDTO>) result).getData());
    }
}
