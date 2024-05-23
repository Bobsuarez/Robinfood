package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_FAMILIES;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_FAMILIES;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static com.robinfood.taxes.constants.PermissionsConstants.UPDATE_FAMILIES;
import static com.robinfood.taxes.constants.GeneralConstants.ACTIVE_STATUS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.constants.PermissionsConstants;
import com.robinfood.taxes.controllers.v1.docs.FamilyControllerDocs;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateFamilyDTO;
import com.robinfood.taxes.dto.v1.model.FamilyDTO;
import com.robinfood.taxes.dto.v1.update.UpdateFamilyDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.FamilyType;
import com.robinfood.taxes.services.FamilyService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/families")
public class FamilyController implements FamilyControllerDocs {

    private final FamilyService familyService;

    private final ModelMapper modelMapper;

    public FamilyController(FamilyService familyService, ModelMapper modelMapper) {
        this.familyService = familyService;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_FAMILIES + "')")
    public ResponseEntity<ApiResponseDTO<List<FamilyDTO>>> list(
        @RequestParam(value = "country_id") Long countryId,
        @RequestParam(value = "family_type_id") Long familyTypeId,
        @RequestParam(required = false) Integer status) {

        log.trace("Request to list families received on controller with countryId {}, "
            + "familyTypeId {} and status {}", countryId, familyTypeId, status);

        List<Family> familyList = familyService.list(countryId, familyTypeId, status);
        log.trace("Families retrieved successfully from service. {}", familyList);

        log.trace("Generating DTO from families");
        List<FamilyDTO> familyDTOList = familyList.stream()
            .map(this::generateFamilyDTOFromFamily)
            .collect(Collectors.toList());
        log.trace("DTO list generated successfully. {}", familyDTOList);

        log.trace("Creating ApiResponse from DTO list.");
        return ResponseEntity.ok(ApiResponseDTO.<List<FamilyDTO>>builder()
            .code(HttpStatus.OK.value())
            .data(familyDTOList)
            .build());
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('" + PermissionsConstants.SERVICE + "') || hasRole('"
        + PermissionsConstants.CREATE_FAMILIES + "')")
    public ResponseEntity<ApiResponseDTO<FamilyDTO>> create(
        @RequestBody @Valid CreateFamilyDTO createFamily)
        throws BusinessRuleException, JsonProcessingException {
        log.trace("Transforming received DTO to entity object: {}.", createFamily);
        Family family = generateFamilyFromCreateFamilyDto(createFamily);
        log.trace("Entity object created successfully: {}.", family);

        Family createdFamily = familyService.create(family);

        log.trace("Generating Family DTO from created family: {}.", createdFamily);
        FamilyDTO familyMapped = generateFamilyDTOFromFamily(createdFamily);
        log.trace("Family DTO generated successfully: {}.", familyMapped);

        log.trace("Creating ApiResponse object from created family: {}.", familyMapped);
        ResponseEntity<ApiResponseDTO<FamilyDTO>>
            response = ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<FamilyDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Family created successfully.")
                .data(familyMapped)
                .build());
        log.trace("ApiResponse object created successfully: {}.", response);

        return response;
    }

    @BasicLog
    @Override
    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + PermissionsConstants.SERVICE + "') || hasRole('"
        + UPDATE_FAMILIES + "')")
    public ResponseEntity<ApiResponseDTO<FamilyDTO>> update(
        @PathVariable Long id, @Valid @RequestBody UpdateFamilyDTO updateFamilyDTO)
        throws BusinessRuleException, JsonProcessingException {

        log.trace("Request received on family controller with body {}", updateFamilyDTO);

        log.trace("Generating family object");
        Family familyObject = generateFamilyFromUpdateDTO(updateFamilyDTO);
        log.trace("Family object generated successfully {}", familyObject);

        Family updatedFamily = familyService.update(id, familyObject);

        log.trace("Generating updated family DTO from created family. {}", updatedFamily);
        FamilyDTO mappedFamily = generateFamilyDTOFromFamily(updatedFamily);
        log.trace("DTO generated successfully. {}", mappedFamily);

        log.trace("Creating ApiResponse");
        ResponseEntity<ApiResponseDTO<FamilyDTO>> response = ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<FamilyDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Family updated successfully")
                .data(mappedFamily)
                .build());
        log.trace("Api response created from updated family successfully. {}", response);

        return response;
    }

    @BasicLog
    @Override
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + DELETE_FAMILIES + "')")
    public ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable Long id)
        throws BusinessRuleException, JsonProcessingException {
        log.trace("Request received on delete family controller with id {}", id);

        familyService.delete(id);
        log.trace("Family deleted successfully on service");

        log.trace("Generating ApiResponse from delete family");
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Family deleted successfully")
                .build());
    }

    private Family generateFamilyFromUpdateDTO(UpdateFamilyDTO updateFamilyDTO) {
        Family family = new Family();
        family.setName(updateFamilyDTO.getName());
        family.setStatus(updateFamilyDTO.getStatus());
        return family;
    }

    private FamilyDTO generateFamilyDTOFromFamily(Family family) {
        FamilyDTO familyDTO = modelMapper.map(family, FamilyDTO.class);
        familyDTO.setFamilyTypeId(family.getFamilyType().getId());
        return familyDTO;
    }

    private static Family generateFamilyFromCreateFamilyDto(
        CreateFamilyDTO createFamily) {
        return Family.builder()
            .familyType(new FamilyType(createFamily.getFamilyTypeId()))
            .countryId(createFamily.getCountryId())
            .name(createFamily.getName())
            .status(ACTIVE_STATUS)
            .build();
    }
}
