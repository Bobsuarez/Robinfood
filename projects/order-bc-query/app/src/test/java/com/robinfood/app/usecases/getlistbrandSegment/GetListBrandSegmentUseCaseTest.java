package com.robinfood.app.usecases.getlistbrandSegment;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.core.dtos.report.salebysegment.BrandSegmentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetListBrandSegmentUseCaseTest {

    @InjectMocks
    private GetListBrandSegmentUseCase getBrandListSegmentCaseUse;

    @Test
    void test_When_InputDataFull_Should_OK_Response_BrandList() {

        List<BrandSegmentDTO> brandSegmentDTO = getBrandListSegmentCaseUse
                .invoke(OrderEntityMock.getDataDefaultList(), OrderEntityMock.getDataDefaultList());

        assertNotNull(brandSegmentDTO);
    }
}
