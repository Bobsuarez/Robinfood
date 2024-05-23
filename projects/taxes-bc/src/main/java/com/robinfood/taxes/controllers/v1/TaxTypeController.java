package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_TAX_TYPE;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_TAX_TYPE;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static com.robinfood.taxes.constants.PermissionsConstants.UPDATE_TAX_TYPE;
import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_TAX_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.controllers.v1.docs.TaxTypeControllerDocs;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateTaxTypeDTO;
import com.robinfood.taxes.dto.v1.model.TaxTypeDTO;
import com.robinfood.taxes.dto.v1.update.UpdateTaxTypeDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.TaxType;
import com.robinfood.taxes.services.TaxTypeService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

@Slf4j
@RestController
@RequestMapping(value = "v1/tax-types")
public class TaxTypeController implements TaxTypeControllerDocs {

    private final TaxTypeService taxTypeService;

    private final ModelMapper modelMapper;

    public TaxTypeController(TaxTypeService taxService, ModelMapper modelMapper) {
        this.taxTypeService = taxService;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_TAX_TYPE + "')")
    public ResponseEntity<ApiResponseDTO<List<TaxTypeDTO>>> list(
        @RequestParam(value = "country_id", required = true) @Valid Long countryId,
        @RequestParam(value = "status", required = false) @Valid Integer status
    ) {

        log.trace("Obtaining tax types in service.");
        List<TaxType> taxTypeList = taxTypeService.list(countryId, status);
        log.trace("Tax Types obtained successfully of service {}.", taxTypeList);

        log.trace("Generating DTO from tax types List");
        List<TaxTypeDTO> taxTypeDTOList = taxTypeList.stream()
            .map(typeTax -> modelMapper.map(typeTax, TaxTypeDTO.class))
            .collect(Collectors.toList());
        log.trace("DTO list generated successfully. {}", taxTypeDTOList);

        log.trace("Creating ApiResponse from list tax types");
        return ResponseEntity.ok(ApiResponseDTO.<List<TaxTypeDTO>>builder()
            .code(HttpStatus.OK.value())
            .data(taxTypeDTOList)
            .build());
    }

    @BasicLog
    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + CREATE_TAX_TYPE + "')")
    public ResponseEntity<ApiResponseDTO<TaxTypeDTO>> create(
        @RequestBody @Valid CreateTaxTypeDTO createTaxTypeDTO)
        throws JsonProcessingException, BusinessRuleException {
        log.trace("Request to create tax type received on controller with body: {}", createTaxTypeDTO);

        log.trace("Generating tax type object from DTO {}", createTaxTypeDTO);
        TaxType taxTypeToCreate = generateTaxTypeFromCreateTaxTypeDTO(createTaxTypeDTO);
        log.trace("Tax type object generated successfully. {}", taxTypeToCreate);

        TaxType createdTypeTax = taxTypeService.create(taxTypeToCreate);
        log.trace("Tax type created successfully on service {}", createdTypeTax);

        log.trace("Generating DTO from created tax type: {}", createdTypeTax);
        TaxTypeDTO taxTypeDTO = modelMapper.map(createdTypeTax, TaxTypeDTO.class);
        log.trace("DTO generated successfully {}", taxTypeDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<TaxTypeDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Tax type created successfully")
                .data(taxTypeDTO)
                .build());
    }


    @BasicLog
    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + UPDATE_TAX_TYPE + "')")
    public ResponseEntity<ApiResponseDTO<TaxTypeDTO>> update(@PathVariable Long id,
        @Valid @RequestBody UpdateTaxTypeDTO updateTaxTypeDTO)
        throws JsonProcessingException, BusinessRuleException {

        log.trace("Generating Tax Type Object from updateTaxTypeDTO {}", updateTaxTypeDTO);
        TaxType taxTypeToUpdate = generateTaxTypeFromUpdateTaxTypeDTO(updateTaxTypeDTO);
        log.trace("Tax type object generated successfully {}", taxTypeToUpdate);

        log.trace("Updating Tax Type in service");
        TaxType taxTypeUpdated = taxTypeService.update(id, taxTypeToUpdate);
        log.trace("Tax type updated successfully in service {}", taxTypeUpdated);

        log.trace("Generating TaxTypeDTO from TaxType updated");
        TaxTypeDTO mapperTaxTypeDTO = modelMapper.map(taxTypeUpdated, TaxTypeDTO.class);
        log.trace("Tax type updated successfully in service {}", mapperTaxTypeDTO);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<TaxTypeDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Tax type updated successfully")
                .data(mapperTaxTypeDTO)
                .build());
    }

    @BasicLog
    @Override
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + DELETE_TAX_TYPE + "')")
    public ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable Long id)
            throws BusinessRuleException, JsonProcessingException {
        log.trace("Request received on delete tax type controller with id {}", id);

        taxTypeService.delete(id);
        log.trace("Tax type deleted successfully on service");

        log.trace("Generating ApiResponse from delete tax type");
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<String>builder()
                        .code(HttpStatus.NO_CONTENT.value())
                        .message("Tax type deleted successfully")
                        .build());
    }

    private static TaxType generateTaxTypeFromCreateTaxTypeDTO(CreateTaxTypeDTO dto) {
        return TaxType.builder()
            .name(dto.getName())
            .countryId(dto.getCountryId())
            .build();
    }

    private static TaxType generateTaxTypeFromUpdateTaxTypeDTO(UpdateTaxTypeDTO updateTaxTypeDTO) {
        return TaxType.builder()
            .name(updateTaxTypeDTO.getName())
            .status(updateTaxTypeDTO.getStatus())
            .build();
    }
}
