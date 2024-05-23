package com.robinfood.app.usecases.getreportsalecompany;

import com.robinfood.app.usecases.getsalereport.GetSaleReportUseCase;
import com.robinfood.core.dtos.report.sale.SaleReportResponseDTO;
import com.robinfood.core.exceptions.AsyncOrderBcException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetReportSaleByCompanyUseCaseTest {

    @Mock
    private GetSaleReportUseCase asyncSaleReportUseCase;

    @InjectMocks
    private GetReportSaleByCompanyUseCase getReportSaleByCompanyUseCase;

    @BeforeEach
    public void setUp() {

        ReflectionTestUtils.setField(
                getReportSaleByCompanyUseCase,
                "IDS_ORDERS_STATUS_CANCELLED_AND_DISCARDED",
                List.of(6L, 9L));
    }

    @Test
    void test_GetReportSaleByCompanyUseCase_When_GetDataFull_Should_Ok() throws AsyncOrderBcException {

        when(asyncSaleReportUseCase
                .invoke(
                        any(LocalDateTime.class),
                        anyLong(),
                        eq(List.of(6L, 9L))))
                .thenReturn(CompletableFuture.completedFuture(new BigDecimal("8900.0")));

        SaleReportResponseDTO resultSaleReportDTO = getReportSaleByCompanyUseCase.invoke(
                List.of(1),
                LocalDateTime.now(),
                List.of("America/Bogota", "America/Sao_Paulo")
        );
        assertThat(resultSaleReportDTO.getCompanyList().size(), is(1));
        assertEquals(resultSaleReportDTO.getCompanyList().get(0).ordersSales.salesCurrentDto.value, BigDecimal.valueOf(8900.0));
        assertEquals(resultSaleReportDTO.getCompanyList().get(0).ordersSales.salesAWeekBeforeDto.value, BigDecimal.valueOf(8900.0));
    }

    @Test
    void test_GetReportSaleByCompanyUseCase_When_AsyncThread_Should_Error_Exception() {

        AsyncOrderBcException asyncOrderBcException = assertThrows(AsyncOrderBcException.class, () -> {
            getReportSaleByCompanyUseCase.invoke(
                    List.of(1),
                    LocalDateTime.now(),
                    List.of("America/Bogota", "America/Sao_Paulo"));
        });

        Assertions.assertEquals("[toSearchInThreadsSumTotal] Error when making query threads",
                asyncOrderBcException.getMessage());
    }
}
