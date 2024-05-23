package com.robinfood.app.controllers.configuration.channel;

import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.configuration.ChannelDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Controller that exposes final point in relation to list channels.
 */
public interface IChannelController {

    /**
     * Channels to configuration
     *
     * @return Return channels
     */
    ResponseEntity<ApiResponseDTO<List<ChannelDTO>>> invoke();
}
