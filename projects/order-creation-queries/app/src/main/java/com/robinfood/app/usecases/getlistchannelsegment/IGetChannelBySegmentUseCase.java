package com.robinfood.app.usecases.getlistchannelsegment;

import com.robinfood.core.dtos.configuration.ChannelDTO;
import com.robinfood.core.dtos.report.salebysegment.ChannelSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.response.ChannelsDTOResponse;

import java.util.List;

/**
 * Use case that returns the list of channels by app
 */
public interface IGetChannelBySegmentUseCase {

    /**
     * retrieve the stores according to the entered criteria
     *
     * @return object with the data
     */
    ChannelsDTOResponse invoke(
            String currencyType,
            List<ChannelSegmentDTO> channelSegmentDTOS,
            List<ChannelDTO> channelDTOList
    );
}
