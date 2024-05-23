package com.robinfood.app.usecases.getchannels;

import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.enums.Result;

/**
 * Use case that returns the list of channels
 */
public interface IGetConfigChannelsUseCase {

    /**
     * retrieve the channels
     *
     * @return object with the data
     */
    Result<ChannelsDTO> invoke();
}
