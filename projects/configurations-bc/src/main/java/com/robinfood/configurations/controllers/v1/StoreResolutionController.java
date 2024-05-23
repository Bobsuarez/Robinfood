package com.robinfood.configurations.controllers.v1;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.constants.ConfigurationsBCConstants;
import com.robinfood.configurations.controllers.v1.docs.StoreResolutionControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.ActivateOrDeactivateDTO;
import com.robinfood.configurations.dto.v1.ResponseResolutionsWithPosDTO;
import com.robinfood.configurations.dto.v1.StoreResolutionDTO;
import com.robinfood.configurations.enums.MessageResponseEnum;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.services.StoreResolutionService;
import com.robinfood.configurations.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "v1/stores/resolutions")
@Validated
public class StoreResolutionController implements StoreResolutionControllerDocs {

    private final StoreResolutionService storeResolutionService;

    public StoreResolutionController(StoreResolutionService storeResolutionService) {
        this.storeResolutionService = storeResolutionService;
    }

    @BasicLog
    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponseDTO<List<ResponseResolutionsWithPosDTO>>> createStoreResolutions(
            @Valid @RequestBody List<StoreResolutionDTO> storeResolutionDTOS
    ) {

        log.info("Request received on store controller [createStoreResolutions] with body: {}",
                JsonUtils.convertToJson(storeResolutionDTOS));

        final List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosDTOS = storeResolutionService
                .create(storeResolutionDTOS);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseDTO.<List<ResponseResolutionsWithPosDTO>>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("Resolutions created successfully")
                        .data(responseResolutionsWithPosDTOS)
                        .build());
    }

    @BasicLog
    @Override
    @PutMapping(value = "/{resolutionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<Object>> updateStoreResolutions(
            @Valid @RequestBody StoreResolutionDTO storeResolutionDTOS,
            @PathVariable("resolutionId") @Min(1) Long resolutionId
    ) throws BusinessRuleException {

        log.info("Request received on Resolution controller [UpdateStoreResolutions] with body: {}",
                JsonUtils.convertToJson(storeResolutionDTOS));

        String dataEmpty = storeResolutionService.update(storeResolutionDTOS, resolutionId);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ApiResponseDTO.builder()
                        .code(HttpStatus.ACCEPTED.value())
                        .message(MessageResponseEnum.UPDATE_MESSAGE.getMessage())
                        .data(dataEmpty)
                        .build());
    }

    @BasicLog
    @Override
    @PatchMapping(value = "/{id}/active", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ApiResponseDTO<String>> activateOrDeactivate(
            @PathVariable("id") Long id,
            @Valid @RequestBody ActivateOrDeactivateDTO activateOrDeactivateDTO
    ) {
        log.info("Request received on store resolutions controller [StoreResolutionController] with body: {}",
                JsonUtils.convertToJson(activateOrDeactivateDTO));

        storeResolutionService.isActivateOrDeactivateByResolutionId(activateOrDeactivateDTO, id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ApiResponseDTO.<String>builder()
                        .code(HttpStatus.ACCEPTED.value())
                        .data(ConfigurationsBCConstants.STRING_EMPTY)
                        .message("Updated resolution successfully")
                        .build());
    }
}
