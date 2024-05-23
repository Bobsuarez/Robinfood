package com.robinfood.app.usecases.getliststoresegment;

import com.robinfood.app.mocks.bysegment.SalesBuildAnswerDTOMock;
import com.robinfood.core.dtos.report.salebysegment.StoreSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.StoresDTOResponse;
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
class GetListStoreSegmentUseCaseTest {

    @InjectMocks
    private GetStoreBySegmentUseCase getListStoreSegmentUseCase;

    @Test
    void Test_invoke_Should_ReturnDataStore_When_InvokeTheUseCase() {

        List<StoreSegmentDTO> storeSegmentDTOS = SalesBuildAnswerDTOMock.getDefault().getSaleSegmentDTO().getCompanies()
                .get(DEFAULT_INTEGER_VALUE).getStores();

        StoresDTOResponse resulStore = getListStoreSegmentUseCase
                .invoke("COP", storeSegmentDTOS, SalesBuildAnswerDTOMock.getDefault().getStoresDTOList());

        Assertions.assertNotNull(resulStore, "Response resulStore");
    }
}
