package com.robinfood.app.usecases.getlistchannelsegment;

import com.robinfood.app.mocks.bysegment.SalesBuildAnswerDTOMock;
import com.robinfood.core.dtos.report.salebysegment.ChannelSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.ChannelsDTOResponse;
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
class GetListChannelSegmentUseCaseTest {

    @InjectMocks
    private GetChannelBySegmentUseCase getListChannelSegmentUseCase;

    @Test
    void Test_invoke_Should_ReturnDataChannels_When_InvokeTheUseCase() {

        List<ChannelSegmentDTO> channelSegmentDTOS = SalesBuildAnswerDTOMock.getDefault().getSaleSegmentDTO().getCompanies()
                .get(DEFAULT_INTEGER_VALUE).getChannels();

        ChannelsDTOResponse resulChannels = getListChannelSegmentUseCase
                .invoke("COP", channelSegmentDTOS, SalesBuildAnswerDTOMock.getDefault().getChannelDTOList());

        Assertions.assertNotNull(resulChannels, "Response resulChannels");
    }
}
