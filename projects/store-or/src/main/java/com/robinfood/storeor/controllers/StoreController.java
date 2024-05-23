package com.robinfood.storeor.controllers;

import com.robinfood.storeor.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;
import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.usecase.getconfigurationbystore.IGetConfigurationByStoreUseCase;
import com.robinfood.storeor.usecase.getconfigurationposbystore.IGetConfigurationPosByStoreUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.POS_BY_STORE_CONFIGURATION;
import static com.robinfood.storeor.configs.constants.APIConstants.STORE_V1;

@Slf4j
@RestController
@RequestMapping(STORE_V1)
public class StoreController implements IStoreController {

    private final IGetConfigurationByStoreUseCase getConfigurationByStoreUseCase;
    private final IGetConfigurationPosByStoreUseCase getConfigurationPosByStoreUseCase;

    public StoreController(IGetConfigurationByStoreUseCase getConfigurationByStoreUseCase,
                           IGetConfigurationPosByStoreUseCase getConfigurationPosByStoreUseCase) {
        this.getConfigurationByStoreUseCase = getConfigurationByStoreUseCase;
        this.getConfigurationPosByStoreUseCase = getConfigurationPosByStoreUseCase;
    }

    @GetMapping("/{storeId}/configuration")
    public ResponseEntity<APIResponseDTO<StoreResponseDTO>> getStore(
            @PathVariable("storeId") Long storeId,
            @RequestParam(value = "includePos", required = false, defaultValue = "false") Boolean includePos
    ) {

        log.info("StoreController() Get Configuration by store: {}", storeId);

        final StoreResponseDTO storeResponseDTO = getConfigurationByStoreUseCase.invoke(storeId, includePos);

        log.info("response configuration store {}", storeResponseDTO);

        AbstractApiResponseBuilderDTO<StoreResponseDTO> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(storeResponseDTO, ApiResponseEnum.RESPONSE_OK_STORE);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());

    }

    @GetMapping("/")
    public ResponseEntity<APIResponseDTO<Page<StoreResponseDTO>>> getStoreList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "company_country_id", required = false) Long companyCountryId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false) Integer size, Sort sort) {

        log.info("StoreController() Get Configuration store list.");

        final Page<StoreResponseDTO> storeResponseDTO =
                getConfigurationByStoreUseCase.invoke(name, companyCountryId, page, size, sort);

        log.info("response configuration store list {}", storeResponseDTO);

        AbstractApiResponseBuilderDTO<Page<StoreResponseDTO>> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(storeResponseDTO, ApiResponseEnum.RESPONSE_OK_STORE);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());

    }

    @GetMapping(POS_BY_STORE_CONFIGURATION)
    public ResponseEntity<APIResponseDTO<List<StorePosDTO>>> getConfigurationPosByStore(
            @PathVariable("storeId") Long storeId) {

        log.info("GetConfigurationPosByStoreController() Get Configuration Pos by store: {}", storeId);

        final List<StorePosDTO> storePosDTOS = getConfigurationPosByStoreUseCase.invoke(storeId);

        log.info("response GetConfigurationPosByStoreController pos store {}", storePosDTOS);

        AbstractApiResponseBuilderDTO<List<StorePosDTO>> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(storePosDTOS, ApiResponseEnum.RESPONSE_OK_POS_BY_STORE);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());
    }

}
