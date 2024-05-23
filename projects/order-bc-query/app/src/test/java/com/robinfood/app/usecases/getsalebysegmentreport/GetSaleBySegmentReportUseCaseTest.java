package com.robinfood.app.usecases.getsalebysegmentreport;

import com.robinfood.app.datamocks.dto.input.DataIdsToFindTheSegmentDTOMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.usecases.getlistpaymentSegment.IGetListPaymentMethodsSegmentUseCase;
import com.robinfood.app.usecases.getordersquerybysegmentstatement.IGetOrdersQueryBySegmentStatementUseCase;
import com.robinfood.core.entities.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetSaleBySegmentReportUseCaseTest {

    @Mock
    private IGetOrdersQueryBySegmentStatementUseCase queryBySegmentStatementUseCase;

    @Mock
    private IGetListPaymentMethodsSegmentUseCase getPaymentMethodsListSegmentCaseUse;

    @InjectMocks
    private GetSaleBySegmentReportUseCase getSaleBySegmentReportUseCase;

    @Test
    void test_AsyncSaleReportUseCaseTest() throws ExecutionException, InterruptedException {

        when(queryBySegmentStatementUseCase.invoke(
                any(LocalDateTime.class),
                any()
        )).thenReturn(OrderEntityMock.getDataDefaultList());

        when(getPaymentMethodsListSegmentCaseUse.filterAndRemoverOrders(
                anyList(),anyList()
        )).thenReturn(OrderEntityMock.getDataDefaultList());

        CompletableFuture<List<OrderEntity>> resultAsync = getSaleBySegmentReportUseCase
                .invoke(DataIdsToFindTheSegmentDTOMock.getDefault(), LocalDateTime.now());

        assertEquals(resultAsync.get().size(), 1);
    }
}
