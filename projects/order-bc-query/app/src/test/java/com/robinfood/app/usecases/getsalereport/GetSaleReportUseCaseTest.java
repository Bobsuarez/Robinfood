package com.robinfood.app.usecases.getsalereport;

import com.robinfood.repository.orders.IOrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetSaleReportUseCaseTest {
    
    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private GetSaleReportUseCase asyncSaleReportUseCase;

    @Test
    void test_AsyncSaleReportUseCaseTest() throws ExecutionException, InterruptedException {

        when(ordersRepository.sumTotalByLocalDateAndCompanyIdAndPaidAndLocalTimeBetweenAndStatusIdNotIn(
                any(LocalDate.class),
                any(LocalTime.class),
                anyLong(),
                any(LocalTime.class),
                anyBoolean(),
                any()
        )).thenReturn(new BigDecimal("100.0"));

        CompletableFuture<BigDecimal> resultAsync = asyncSaleReportUseCase
                .invoke(LocalDateTime.now(), 1L, List.of(6L, 9L));

        assertEquals(resultAsync.get(), BigDecimal.valueOf(100.0));
    }
}
