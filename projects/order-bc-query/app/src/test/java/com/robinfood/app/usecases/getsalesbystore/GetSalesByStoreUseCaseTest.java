package com.robinfood.app.usecases.getsalesbystore;

import com.robinfood.app.datamocks.dto.input.DataIdsToFindTheSegmentDTOMock;
import com.robinfood.app.datamocks.dto.output.sale.OutItemsByPaymentMethodsDTOMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.usecases.getlistpaymentbystore.GetListPaymentMethodsByStoreUseCase;
import com.robinfood.app.usecases.getordersquerybysegmentstatement.IGetOrdersQueryBySegmentStatementUseCase;
import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.dtos.report.salebystore.GetSaleByStoreResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.AssertionErrors;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetSalesByStoreUseCaseTest {

    @Mock
    private GetListPaymentMethodsByStoreUseCase getListPaymentMethodsByStoreUseCase;

    @Mock
    private IGetOrdersQueryBySegmentStatementUseCase queryBySegmentStatementUseCase;

    @InjectMocks
    private GetSalesByStoreUseCase getSalesByStoreUseCase;

    @Test
    void test_When_IdIsFoundBySegments_Should_ResponseOk() {

        when(queryBySegmentStatementUseCase.invoke(any(LocalDateTime.class), any(DataIdsToFindTheSegment.class)))
                .thenReturn(OrderEntityMock.getDataDefaultList());

        when(getListPaymentMethodsByStoreUseCase.invoke(anyList(), anyList()))
                .thenReturn(List.of(OutItemsByPaymentMethodsDTOMock.getDefault()));

        GetSaleByStoreResponseDTO resultSaleReportDTO = getSalesByStoreUseCase.invoke(
                DataIdsToFindTheSegmentDTOMock.getDefault());

        AssertionErrors.assertNotNull("the object is built", resultSaleReportDTO);
    }

    @Test
    void test_When_GetDataOrderListCurrentAndLastWeekIsNull_Should_Ok_Response() {

        when(queryBySegmentStatementUseCase.invoke(eq(LocalDateTime.parse("2023-03-10T21:23")), any(DataIdsToFindTheSegment.class)))
                .thenReturn(List.of());

        when(queryBySegmentStatementUseCase.invoke(eq(LocalDateTime.parse("2023-03-17T21:23:00.212546300")), any(DataIdsToFindTheSegment.class)))
                .thenReturn(OrderEntityMock.getDataDefaultList());

        when(getListPaymentMethodsByStoreUseCase.invoke(anyList(), anyList()))
                .thenReturn(List.of(OutItemsByPaymentMethodsDTOMock.getDefault()));

        GetSaleByStoreResponseDTO resultSaleReportDTO = getSalesByStoreUseCase.invoke(
                DataIdsToFindTheSegmentDTOMock.getDefault());

        AssertionErrors.assertNotNull("the object is built", resultSaleReportDTO);
    }

    @Test
    void test_When_GetDataOrderListCurrentIsNull_Should_Ok_Response() {

        when(queryBySegmentStatementUseCase.invoke(eq(LocalDateTime.now()), any(DataIdsToFindTheSegment.class)))
                .thenReturn(List.of());

        when(queryBySegmentStatementUseCase.invoke(
                eq(LocalDateTime.parse("2023-03-15T13:00:00")),
                any(DataIdsToFindTheSegment.class)
        )).thenReturn(OrderEntityMock.getDataDefaultList());

        when(getListPaymentMethodsByStoreUseCase.invoke(anyList(), anyList()))
                .thenReturn(List.of(OutItemsByPaymentMethodsDTOMock.getDefault()));

        GetSaleByStoreResponseDTO resultSaleReportDTO = getSalesByStoreUseCase.invoke(
                DataIdsToFindTheSegmentDTOMock.getDefault());

        AssertionErrors.assertNotNull("the object is built", resultSaleReportDTO);
    }
}