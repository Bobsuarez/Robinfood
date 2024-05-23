package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.LIST_FAMILY_TYPES;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.controllers.v1.docs.FamilyTypeControllerDocs;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.model.FamilyTypeDTO;
import com.robinfood.taxes.models.FamilyType;
import com.robinfood.taxes.services.FamilyTypeService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/family-types")
public class FamilyTypeController implements FamilyTypeControllerDocs {

    private final FamilyTypeService familyTypeService;

    private final ModelMapper modelMapper;

    public FamilyTypeController(FamilyTypeService familyTypeService, ModelMapper modelMapper) {
        this.familyTypeService = familyTypeService;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_FAMILY_TYPES + "')")
    public ResponseEntity<ApiResponseDTO<List<FamilyTypeDTO>>> findAll() {

        log.trace("Obtaining family types in service.");
        List<FamilyType> familyTypesList = familyTypeService.findAll();
        log.trace("Family types obtained successfully of service {}.", familyTypesList);

        log.trace("Generating DTO from familyTypesList");
        List<FamilyTypeDTO> familyTypesDTOList = familyTypesList.stream()
            .map(familyType -> modelMapper.map(familyType, FamilyTypeDTO.class))
            .collect(Collectors.toList());
        log.trace("DTO list generated successfully. {}", familyTypesDTOList);

        log.trace("Creating ApiResponse from list family types");
        return ResponseEntity.ok(ApiResponseDTO.<List<FamilyTypeDTO>>builder()
            .code(HttpStatus.OK.value())
            .data(familyTypesDTOList)
            .build());
    }

}
