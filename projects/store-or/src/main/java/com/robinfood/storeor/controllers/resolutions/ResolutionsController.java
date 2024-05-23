package com.robinfood.storeor.controllers.resolutions;

import com.robinfood.storeor.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.findAllResolutions.SearchResolutionDTO;
import com.robinfood.storeor.dtos.response.DataResolutionResponseDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.exceptions.restexceptionhandlers.ResolutionCrudException;
import com.robinfood.storeor.usecase.createresolutions.ICreateResolutionsUseCase;
import com.robinfood.storeor.usecase.activateordeactivateresolutions.IActivateOrDeactivateResolutionsUseCase;
import com.robinfood.storeor.usecase.findallresolutions.IFindAllResolutionsUseCase;
import com.robinfood.storeor.usecase.updateresolutions.IUpdateResolutionsUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.ACTIVE_RESOLUTION;
import static com.robinfood.storeor.configs.constants.APIConstants.POS_STORES_V1;
import static com.robinfood.storeor.configs.constants.APIConstants.GET_ALL;
import static com.robinfood.storeor.configs.constants.APIConstants.RESOLUTIONS;
import static com.robinfood.storeor.configs.constants.APIConstants.RESOLUTION_ID;


@Slf4j
@RestController
@RequestMapping(POS_STORES_V1 + RESOLUTIONS)
public class ResolutionsController implements IResolutionsController {

    private final ICreateResolutionsUseCase createResolutionsUseCase;
    private final IUpdateResolutionsUseCase updateResolutionsUseCase;
    private final IActivateOrDeactivateResolutionsUseCase activateOrDeactivateResolutionsUseCase;
    private final IFindAllResolutionsUseCase findAllResolutionsUseCase;

    public ResolutionsController(
            ICreateResolutionsUseCase createResolutionsUseCase,
            IUpdateResolutionsUseCase updateResolutionsUseCase,
            IActivateOrDeactivateResolutionsUseCase activateOrDeactivateResolutionsUseCase,
            IFindAllResolutionsUseCase findAllResolutionsUseCase
    ) {

        this.createResolutionsUseCase = createResolutionsUseCase;
        this.updateResolutionsUseCase = updateResolutionsUseCase;
        this.activateOrDeactivateResolutionsUseCase = activateOrDeactivateResolutionsUseCase;
        this.findAllResolutionsUseCase = findAllResolutionsUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<APIResponseDTO<List<ResponseResolutionsWithPosDTO>>> createStoreResolutions(
            HttpServletRequest httpServletRequest,
            @Valid @RequestBody() final StoreResolutionsDTO storeResolutions
    ) throws ResolutionCrudException {

        log.info("Create Resolutions has started with request: {}", storeResolutions);

        AbstractApiResponseBuilderDTO<List<ResponseResolutionsWithPosDTO>> apiResponseDTOBuilder;

        List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosDTOs = createResolutionsUseCase
                .invoke(storeResolutions);

        log.info("Resolutions response created successfully ");

        apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(
                responseResolutionsWithPosDTOs,
                ApiResponseEnum.RESPONSE_CREATED_RESOLUTIONS
        );

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(RESOLUTION_ID)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<APIResponseDTO<ResolutionDTO>> updateStoreResolutions(
            HttpServletRequest httpServletRequest,
            @Valid @RequestBody() final ResolutionDTO resolutionDTO,
            @PathVariable(value = "resolutionId") final Long resolutionId
    ) throws ResolutionCrudException {

        log.info("Update Resolutions has started with request {} and resolution Id", resolutionDTO, resolutionId);

        updateResolutionsUseCase.invoke(resolutionDTO, resolutionId);

        log.info("Resolutions response updated successfully ");

        AbstractApiResponseBuilderDTO<ResolutionDTO> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(
                ResolutionDTO.builder().build(),
                ApiResponseEnum.RESPONSE_UPDATE_RESOLUTIONS
        );

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.ACCEPTED);
    }

    @Override
    @PatchMapping(RESOLUTION_ID + ACTIVE_RESOLUTION)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<APIResponseDTO<ResolutionDTO>> activateOrDeactivate(
            HttpServletRequest httpServletRequest,
            @Valid @RequestBody final ActivateOrDeactivateDTO activateOrDeactivateDTO,
            @PathVariable(value = "resolutionId") final Long resolutionId
    ) throws ResolutionCrudException {

        log.info("Update State of Resolutions has started with request {} and resolution Id",
                activateOrDeactivateDTO,
                resolutionId);

        activateOrDeactivateResolutionsUseCase.invoke(activateOrDeactivateDTO, resolutionId);

        log.info("Resolutions response updated status successfully ");

        AbstractApiResponseBuilderDTO<ResolutionDTO> apiResponseDTOBuilder = new OkAbstractApiResponseBuilderDTO<>();
        apiResponseDTOBuilder.build(
                ResolutionDTO.builder().build(),
                ApiResponseEnum.RESPONSE_UPDATE_RESOLUTIONS
        );

        return new ResponseEntity<>(apiResponseDTOBuilder.getApiResponseDTO(), HttpStatus.ACCEPTED);
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<APIResponseDTO<DataResolutionResponseDTO>> findAllResolutions(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) String valueCustomFilter,
            @RequestParam(required = false) String orderByEndDateResolution,
            @RequestParam(required = false) Boolean withPos,
            @RequestParam(required = false) Long resolutionId
    ) throws ResolutionCrudException{

        final DataResolutionResponseDTO resolutionsListResponseDTO =
                findAllResolutionsUseCase.invoke(
                        new SearchResolutionDTO(
                                orderByEndDateResolution, page, resolutionId, size, status, valueCustomFilter, withPos
                        )
                );

        AbstractApiResponseBuilderDTO<DataResolutionResponseDTO> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(resolutionsListResponseDTO, ApiResponseEnum.RESPONSE_OK_FIND_ALL_RESOLUTIONS);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());
    }
}
