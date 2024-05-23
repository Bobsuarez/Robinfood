package com.robinfood.app.usecases.getlistpaymentsegment;

import com.robinfood.app.mocks.bysegment.SalesBuildAnswerDTOMock;
import com.robinfood.core.dtos.report.salebysegment.PaymentMethodSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.PaymentMethodsDTOResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetListPaymentSegmentUseCaseTest {

    @InjectMocks
    private GetPaymentBySegmentUseCase getListPaymentSegmentUseCase;

    @Test
    void Test_invoke_Should_ReturnDataPaymentMethods_When_InvokeTheUseCase() {

        List<PaymentMethodSegmentDTO> brandSegmentDTOS = SalesBuildAnswerDTOMock.getDefault().getSaleSegmentDTO().getCompanies()
                .get(DEFAULT_INTEGER_VALUE).getPaymentMethods();

        PaymentMethodsDTOResponse resulPaymentMethods = getListPaymentSegmentUseCase
                .invoke("COP", brandSegmentDTOS, SalesBuildAnswerDTOMock.getDefault().getPaymentDTOList());

        Assertions.assertNotNull(resulPaymentMethods, "Response resulBrands");
    }
}
