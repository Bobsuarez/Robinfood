package com.robinfood.app.usecases.getlistbrandsegment;

import com.robinfood.app.mocks.bysegment.SalesBuildAnswerDTOMock;
import com.robinfood.core.dtos.report.salebysegment.BrandSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.BrandsDTOResponse;
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
class GetListBrandSegmentUseCaseTest {

    @InjectMocks
    private GetBrandBySegmentUseCase getListBrandSegmentUseCase;

    @Test
    void Test_invoke_Should_ReturnDataBrands_When_InvokeTheUseCase() {

        List<BrandSegmentDTO> brandSegmentDTOS = SalesBuildAnswerDTOMock.getDefault().getSaleSegmentDTO().getCompanies()
                .get(DEFAULT_INTEGER_VALUE).getBrands();

        BrandsDTOResponse resulBrands = getListBrandSegmentUseCase
                .invoke("COP", brandSegmentDTOS, SalesBuildAnswerDTOMock.getDefault().getBrandDTOList());

        Assertions.assertNotNull(resulBrands, "Response resulBrands");
    }

}