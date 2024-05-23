package com.robinfood.app.usecases.getsalesbysegment;

import com.robinfood.app.datamocks.dto.input.DataIdsToFindTheSegmentDTOMock;
import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.app.usecases.getsalebysegmentreport.GetSaleBySegmentReportUseCase;
import com.robinfood.app.usecases.getlistbrandSegment.GetListBrandSegmentUseCase;
import com.robinfood.app.usecases.getlistchannelsSegment.GetListChannelSegmentUseCase;
import com.robinfood.app.usecases.getlistpaymentSegment.GetListPaymentMethodsSegmentUseCase;
import com.robinfood.app.usecases.getliststoreSegment.GetListStoreSegmentUseCase;
import com.robinfood.core.dtos.report.salebysegment.BrandSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.ChannelSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentResponseDTO;
import com.robinfood.core.dtos.report.salebysegment.PaymentMethodSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.StoreSegmentDTO;
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
import org.springframework.test.util.AssertionErrors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetSalesBySegmentUseCaseTest {

    @Mock
    private GetListBrandSegmentUseCase getBrandListSegmentCaseUse;

    @Mock
    private GetListStoreSegmentUseCase getStoreListSegmentCaseUse;

    @Mock
    private GetListChannelSegmentUseCase getChannelListSegmentCaseUse;

    @Mock
    private GetListPaymentMethodsSegmentUseCase getPaymentMethodsListSegmentCaseUse;

    @Mock
    private GetSaleBySegmentReportUseCase asyncSaleBySegmentReportUseCase;

    @InjectMocks
    private GetSalesBySegmentUseCase getSalesBySegmentUseCase;

    @BeforeEach
    public void setUp() {



        when(getPaymentMethodsListSegmentCaseUse.filterAndRemoverOrders(anyList(), anyList()))
                .thenReturn(OrderEntityMock.getDataDefaultList());

        when(getBrandListSegmentCaseUse.invoke(anyList(), anyList()))
                .thenReturn(List.of(BrandSegmentDTO.builder().build()));

        when(getStoreListSegmentCaseUse.invoke(anyList(), anyList()))
                .thenReturn(List.of(StoreSegmentDTO.builder().build()));

        when(getChannelListSegmentCaseUse.invoke(anyList(), anyList()))
                .thenReturn(List.of(ChannelSegmentDTO.builder().build()));

        when(getPaymentMethodsListSegmentCaseUse.getListPaymentMethodsSegment(anyList(), anyList(), anyList()))
                .thenReturn(List.of(PaymentMethodSegmentDTO.builder().build()));

    }

    @Test
    void test_When_GetData_Should_Ok_Response() throws AsyncOrderBcException {

        when(asyncSaleBySegmentReportUseCase.invoke(any(DataIdsToFindTheSegment.class), any(LocalDateTime.class)))
                .thenReturn(CompletableFuture.completedFuture(OrderEntityMock.getDataDefaultList()));

        GetSaleBySegmentResponseDTO resultSaleReportDTO = getSalesBySegmentUseCase.invoke(
                List.of("America/Bogota", "America/Sao_Paulo"),
                DataIdsToFindTheSegmentDTOMock.getDefault());

        AssertionErrors.assertNotNull("the object is built", resultSaleReportDTO);
    }

    @Test
    void test_When_GetDataNotFilters_Should_Ok_Response() throws AsyncOrderBcException {

        when(asyncSaleBySegmentReportUseCase.invoke(any(DataIdsToFindTheSegment.class), any(LocalDateTime.class)))
                .thenReturn(CompletableFuture.completedFuture(OrderEntityMock.getDataDefaultList()));

        GetSaleBySegmentResponseDTO resultSaleReportDTO = getSalesBySegmentUseCase.invoke(
                List.of("America/Bogota", "America/Sao_Paulo"),
                DataIdsToFindTheSegmentDTOMock.getDefault());

        AssertionErrors.assertNotNull("the object is built", resultSaleReportDTO);
    }

    @Test
    void test_GetReportSaleBySegmentUseCase_When_AsyncThread_Should_Error_Exception() {

        AsyncOrderBcException asyncOrderBcException = assertThrows(AsyncOrderBcException.class, () -> {
            getSalesBySegmentUseCase.invoke(
                    List.of("America/Bogota", "America/Sao_Paulo"),
                    DataIdsToFindTheSegmentDTOMock.getDefault());
        });

        Assertions.assertEquals("[toSearchInThreadsSumTotal] Error when making query threads",
                asyncOrderBcException.getMessage());
    }

}