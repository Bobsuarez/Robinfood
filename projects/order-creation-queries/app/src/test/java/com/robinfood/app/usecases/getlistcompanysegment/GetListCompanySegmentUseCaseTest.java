package com.robinfood.app.usecases.getlistcompanysegment;

import com.robinfood.app.mocks.bysegment.SalesBuildAnswerDTOMock;
import com.robinfood.app.usecases.getlistbrandsegment.IGetBrandBySegmentUseCase;
import com.robinfood.app.usecases.getlistchannelsegment.IGetChannelBySegmentUseCase;
import com.robinfood.app.usecases.getlistpaymentsegment.IGetPaymentBySegmentUseCase;
import com.robinfood.app.usecases.getliststoresegment.IGetStoreBySegmentUseCase;
import com.robinfood.core.dtos.report.salebysegment.response.BrandsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.ChannelsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.CompanyDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.PaymentMethodsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.StoresDTOResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetListCompanySegmentUseCaseTest {

    @Mock
    private IGetBrandBySegmentUseCase getListBrandSegment;

    @Mock
    private IGetChannelBySegmentUseCase getListChannelSegment;

    @Mock
    private IGetStoreBySegmentUseCase getListStoreSegment;

    @Mock
    private IGetPaymentBySegmentUseCase getListPaymentSegment;

    @InjectMocks
    private GetListCompanyBySegmentUseCase getListCompanySegment;

    @Test
    void Test_invoke_Should_ReturnDataSegment_When_InvokeTheUseCase() {

        when(getListBrandSegment.invoke(anyString(), anyList(), anyList())).thenReturn(BrandsDTOResponse.builder().build());
        when(getListChannelSegment.invoke(anyString(), anyList(), anyList())).thenReturn(ChannelsDTOResponse.builder().build());
        when(getListStoreSegment.invoke(anyString(), anyList(), anyList())).thenReturn(StoresDTOResponse.builder().build());
        when(getListPaymentSegment.invoke(anyString(), anyList(), anyList())).thenReturn(PaymentMethodsDTOResponse.builder().build());

        List<CompanyDTOResponse> resultInvoke = getListCompanySegment.invoke(SalesBuildAnswerDTOMock.getDefault());

        Assertions.assertNotNull(resultInvoke, "Response SaleSegmentResponseDTO");
    }
}
