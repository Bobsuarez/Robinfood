package com.robinfood.configurationsposbc.controllers;

import com.robinfood.configurationsposbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.configurationsposbc.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.configurationsposbc.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.configurationsposbc.dtos.pos.PosResponseDTO;
import com.robinfood.configurationsposbc.enums.ApiResponseEnum;
import com.robinfood.configurationsposbc.usecase.getconfigurationposstoreuser.IGetConfigurationPosStoreUserUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.configurationsposbc.configs.constans.APIConstants.CONFIGURATION_POS_V1;

@Slf4j
@RestController
@RequestMapping(CONFIGURATION_POS_V1)
public class ConfigurationPosController implements IConfigurationPosController {

    private final IGetConfigurationPosStoreUserUseCase getConfigurationPosStoreUserUseCase;

    public ConfigurationPosController(IGetConfigurationPosStoreUserUseCase getConfigurationPosStoreUserUseCase) {
        this.getConfigurationPosStoreUserUseCase = getConfigurationPosStoreUserUseCase;
    }

    @Override
    @GetMapping("/pos")
    public ResponseEntity<APIResponseDTO<PosResponseDTO>> getConfigurationPosByStoreAndUser(
            @RequestParam(value = "storeId") Long storeId,
            @RequestParam(value = "userId") Long userId) {

        log.info("ConfigurationPosController() Get Configurations Pos By StoreId: {} and UserId: {}", storeId, userId);

        final PosResponseDTO posResponseDTO = getConfigurationPosStoreUserUseCase.invoke(storeId, userId);

        AbstractApiResponseBuilderDTO<PosResponseDTO> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();

        responseBuilderDTO.build(posResponseDTO, ApiResponseEnum.RESPONSE_OK_CONFIGURATION_POS_BY_STORE_USER);

        log.info("ConfigurationPosController() Get Configurations Pos By StoreId: {} and UserId: {} Response: {} "
                , storeId, userId, responseBuilderDTO.getApiResponseDTO());

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());

    }
}
