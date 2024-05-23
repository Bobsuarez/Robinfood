package com.robinfood.app.controllers.configuration.channel;

import com.robinfood.app.usecases.getchannels.IGetConfigChannelsUseCase;
import com.robinfood.core.dtos.ApiResponseDTO;
import com.robinfood.core.dtos.configuration.ChannelDTO;
import com.robinfood.core.dtos.configuration.ChannelsDTO;
import com.robinfood.core.enums.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.CHANNELS_V1;
import static com.robinfood.core.constants.APIConstants.CHANNELS;

@RequestMapping(CHANNELS_V1)
@RestController
@Slf4j
public class ChannelController implements IChannelController {

    public final IGetConfigChannelsUseCase getConfigChannelsUseCase;

    public ChannelController(IGetConfigChannelsUseCase getConfigChannelsUseCase) {
        this.getConfigChannelsUseCase = getConfigChannelsUseCase;
    }

    @Override
    @GetMapping(CHANNELS)
    public ResponseEntity<ApiResponseDTO<List<ChannelDTO>>> invoke() {

        log.info("Receiving request get Channels");

        final Result<ChannelsDTO> getChannelsUseCase = getConfigChannelsUseCase.invoke();
        final HttpStatus httpStatus;

        ApiResponseDTO<List<ChannelDTO>> apiResponseDTO;

        final List<ChannelDTO> getChannels =  ((Result.Success<ChannelsDTO>) getChannelsUseCase).getData().getContent();

        httpStatus = HttpStatus.OK;
        apiResponseDTO = new ApiResponseDTO<>(
                getChannels,
                httpStatus
        );

        return new ResponseEntity<>(apiResponseDTO, httpStatus);
    }
}
