package com.robinfood.storeor.controllers;

import com.robinfood.storeor.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.user.PosConfigurationResponseDTO;
import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.usecase.getposconfiguration.IGetPosConfigurationByUserIdUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.storeor.configs.constants.APIConstants.POS_CONFIGURATION;
import static com.robinfood.storeor.configs.constants.APIConstants.USER_V1;

@Slf4j
@RestController
@RequestMapping(USER_V1)
public class UserController implements IUserController {

    private final IGetPosConfigurationByUserIdUseCase getPosConfigurationByUserIdUseCase;

    public UserController(IGetPosConfigurationByUserIdUseCase getPosConfigurationByUserIdUseCase) {
        this.getPosConfigurationByUserIdUseCase = getPosConfigurationByUserIdUseCase;
    }

    @Override
    @GetMapping("/{userId}" + POS_CONFIGURATION)
    public ResponseEntity<APIResponseDTO<PosConfigurationResponseDTO>> getPosConfiguration(
            @PathVariable("userId") Long userId
    ) {

        log.info("Get pos configuration for user: {}", userId);
        PosConfigurationResponseDTO posConfigurationResponseDTO = getPosConfigurationByUserIdUseCase.invoke(userId);

        log.info("PosConfigurationResponseDTO " + posConfigurationResponseDTO);
        AbstractApiResponseBuilderDTO<PosConfigurationResponseDTO> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(
                posConfigurationResponseDTO,
                ApiResponseEnum.RESPONSE_OK_POS_CONFIGURATION
        );

        return ResponseEntity.ok().body(apiResponseDTOBuilder.getApiResponseDTO());
    }
}
