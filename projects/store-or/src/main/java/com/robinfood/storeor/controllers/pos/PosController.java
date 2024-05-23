package com.robinfood.storeor.controllers.pos;

import com.robinfood.storeor.dtos.PosDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.configurationpos.StoreCreatePosDTO;
import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivatePosDTO;
import com.robinfood.storeor.dtos.listposresponse.PosListResponseDTO;
import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;
import com.robinfood.storeor.usecase.activateordeactivatepos.IActivateOrDeactivatePosConfigurationsUseCase;
import com.robinfood.storeor.usecase.getlistpos.IGetListPos;
import com.robinfood.storeor.usecase.pos.createposconfiguration.ICreatePosConfigurationsUseCase;
import com.robinfood.storeor.usecase.updatepos.IUpdatePosUseCase;
import com.robinfood.storeor.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.robinfood.storeor.configs.constants.APIConstants.ACTIVE_POS;
import static com.robinfood.storeor.configs.constants.APIConstants.ALL;
import static com.robinfood.storeor.configs.constants.APIConstants.DEFAULT_LONG;
import static com.robinfood.storeor.configs.constants.APIConstants.DEFAULT_LONG_ONE;
import static com.robinfood.storeor.configs.constants.APIConstants.POS;
import static com.robinfood.storeor.configs.constants.APIConstants.POS_ID;
import static com.robinfood.storeor.configs.constants.APIConstants.POS_STORES_V1;
import static com.robinfood.storeor.enums.logs.ApplicationLogEnum.ORDER_START_UPDATE_POS;
import static com.robinfood.storeor.enums.logs.ApplicationLogEnum.ORDER_SUCCESSFULLY_UPDATE_POS;

@Slf4j
@RestController
@RequestMapping(POS_STORES_V1 + POS)
public class PosController implements IPosController {

    private final ICreatePosConfigurationsUseCase createPosConfigurationsUseCase;
    private final IUpdatePosUseCase updatePosUseCase;
    private final IActivateOrDeactivatePosConfigurationsUseCase activateOrDeactivatePosConfigurationsUseCase;
    private final IGetListPos getListPos;

    public PosController(
            IActivateOrDeactivatePosConfigurationsUseCase activateOrDeactivatePosConfigurationsUseCase,
            ICreatePosConfigurationsUseCase createPosConfigurationsUseCase,
            IUpdatePosUseCase updatePosUseCase,
            IGetListPos getListPos) {

        this.activateOrDeactivatePosConfigurationsUseCase = activateOrDeactivatePosConfigurationsUseCase;
        this.createPosConfigurationsUseCase = createPosConfigurationsUseCase;
        this.updatePosUseCase = updatePosUseCase;
        this.getListPos = getListPos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<APIResponseDTO<StoreCreatePosDTO>> createStorePos(
            HttpServletRequest httpServletRequest,
            @Valid @RequestBody() final StoreCreatePosDTO storeCreatePosDTO
    ) {

        AbstractApiResponseBuilderDTO<StoreCreatePosDTO> apiResponseDTOBuilder;

        this.createPosConfigurationsUseCase.invoke(storeCreatePosDTO);

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(
                null,
                ApiResponseEnum.RESPONSE_CREATED_POS
        );

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(POS_ID)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<APIResponseDTO<String>> updatePos(
            @Valid @RequestBody PosDTO posDTO,
            @PathVariable("id") @NotNull @Min(1) Long posId
    ) throws PosException {

        log.info(ORDER_START_UPDATE_POS.getMessage(),
                ObjectMapperUtils.objectToJson(posDTO),
                posId
        );

        updatePosUseCase.invoke(posId, posDTO);

        log.info(ORDER_SUCCESSFULLY_UPDATE_POS.getMessage());

        AbstractApiResponseBuilderDTO<String> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(
                ObjectMapperUtils.getObjectEmpty(),
                ApiResponseEnum.RESPONSE_UPDATE_POSS
        );

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.ACCEPTED);
    }

    @Override
    @PatchMapping(POS_ID + ACTIVE_POS)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<APIResponseDTO<String>> activateOrDeactivate(
            HttpServletRequest httpServletRequest,
            @Valid @RequestBody final ActivateOrDeactivatePosDTO activateOrDeactivatePosDTO,
            @PathVariable(value = "id") final Long posId
    ) throws PosException {

        log.info("Update State of pos has started with request {} and resolution Id",
                ObjectMapperUtils.objectToJson(activateOrDeactivatePosDTO),
                posId);

        activateOrDeactivatePosConfigurationsUseCase.invoke(activateOrDeactivatePosDTO, posId);

        log.info("Pos response updated status successfully ");

        AbstractApiResponseBuilderDTO<String> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(
                ObjectMapperUtils.getObjectEmpty(),
                ApiResponseEnum.RESPONSE_UPDATE_RESOLUTIONS
        );

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.ACCEPTED);
    }

    @Override
    @GetMapping(ALL)
    public ResponseEntity<APIResponseDTO<Page<PosListResponseDTO>>> getPosList(
            @Valid @Min(1) @RequestParam(value = "page") Integer page,
            @RequestParam(value = "posName", required = false) String posName,
            @Valid @Min(1) @RequestParam(value = "size") Integer size,
            @RequestParam(value = "status", required = false) Boolean status,
            @RequestParam(value = "storeId", required = false) Long storeId,
            Sort sort) {

        log.info("PosController() Get Pos List. ");

        Long statusLong = null;

        if (status != null) {
            statusLong = DEFAULT_LONG_ONE;
            if (status.equals(Boolean.FALSE)) {
                statusLong = DEFAULT_LONG;
            }
        }

        final Page<PosListResponseDTO> posListResponseDTOS =
                getListPos.invoke(page, posName, size, statusLong, storeId, sort);

        AbstractApiResponseBuilderDTO<Page<PosListResponseDTO>> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(posListResponseDTOS, ApiResponseEnum.RESPONSE_OK_POS_CONFIGURATION);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());
    }
}
