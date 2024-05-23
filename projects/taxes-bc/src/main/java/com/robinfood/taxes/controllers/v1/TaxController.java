package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_TAXES;
import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_TAXES;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_TAXES;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static com.robinfood.taxes.constants.PermissionsConstants.UPDATE_TAXES;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.controllers.v1.docs.TaxControllerDocs;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.TaxCompleteDTO;
import com.robinfood.taxes.dto.v1.create.CreateTaxDTO;
import com.robinfood.taxes.dto.v1.model.TaxDTO;
import com.robinfood.taxes.dto.v1.update.UpdateTaxDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxType;
import com.robinfood.taxes.services.TaxService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "v1/taxes")
public class TaxController implements TaxControllerDocs {

    private final TaxService taxService;

    private final ModelMapper modelMapper;

    public TaxController(TaxService taxService, ModelMapper modelMapper) {
        this.taxService = taxService;
        this.modelMapper = modelMapper;
    }


    @BasicLog
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_TAXES + "')")
    public ResponseEntity<ApiResponseDTO<List<TaxCompleteDTO>>> list(
        @RequestParam(value = "family_id") @Min(1) Long familyId)
        throws JsonProcessingException, BusinessRuleException {

        log.trace("Obtaining tax List in service.");
        List<Tax> taxList = taxService.list(familyId);
        log.trace("Tax List obtained successfully of service {}.", taxList);

        log.trace("Generating DTO from Tax List");
        List<TaxCompleteDTO> taxesDTOList = taxList.stream()
            .map(tax -> modelMapper.map(tax, TaxCompleteDTO.class))
            .collect(Collectors.toList());
        log.trace("DTO list generated successfully. {}", taxesDTOList);

        log.trace("Creating ApiResponse from Tax List");
        return ResponseEntity.ok(ApiResponseDTO.<List<TaxCompleteDTO>>builder()
            .code(HttpStatus.OK.value())
            .data(taxesDTOList)
            .build());
    }

    @BasicLog
    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + CREATE_TAXES + "')")
    public ResponseEntity<ApiResponseDTO<TaxDTO>> create(
        @RequestBody @Valid CreateTaxDTO createTaxDTO)
        throws JsonProcessingException, BusinessRuleException {

        log.trace("Generating tax object from DTO {}", createTaxDTO);
        Tax taxToCreate = generateTaxFromCreateTaxDTO(createTaxDTO);
        log.trace("Tax object generated successfully. {}", taxToCreate);

        Tax createdTax = taxService.create(taxToCreate);
        log.trace("Tax created successfully on service {}", createdTax);

        log.trace("Generating DTO from created tax {}", createdTax);
        TaxDTO taxDTO = modelMapper.map(createdTax, TaxDTO.class);
        log.trace("DTO generated successfully {}", taxDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<TaxDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Tax created successfully")
                .data(taxDTO)
                .build());
    }

    @BasicLog
    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + UPDATE_TAXES + "')")
    public ResponseEntity<ApiResponseDTO<TaxDTO>> update(@PathVariable Long id,
        @Valid @RequestBody UpdateTaxDTO updateTaxDTO)
        throws JsonProcessingException, BusinessRuleException {

        log.trace("Generating Tax Object from updateTaxTypeDTO {}", updateTaxDTO);
        Tax taxToUpdate = generateTaxFromUpdateTaxDTO(updateTaxDTO);
        log.trace("Tax object generated successfully {}", taxToUpdate);

        log.trace("Updating Tax in service");
        Tax taxUpdated = taxService.update(id, taxToUpdate);
        log.trace("Tax updated successfully in service {}", taxUpdated);

        log.trace("Generating TaxDTO from Tax updated");
        TaxDTO mapperTypeDTO = modelMapper.map(taxUpdated, TaxDTO.class);
        log.trace("Tax updated successfully in service {}", mapperTypeDTO);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<TaxDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Tax updated successfully")
                .data(mapperTypeDTO)
                .build());
    }

    @BasicLog
    @Override
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + DELETE_TAXES + "')")
    public ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable Long id)
        throws JsonProcessingException, BusinessRuleException {

        log.trace("Request received on delete tax controller with id {}", id);

        taxService.delete(id);
        log.trace("Tax deleted successfully on service");

        log.trace("Generating ApiResponse from delete tax");

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Tax deleted successfully")
                .build());
    }

    private static Tax generateTaxFromCreateTaxDTO(CreateTaxDTO dto) {
        return Tax.builder()
            .value(dto.getValue())
            .name(dto.getName())
            .description(dto.getDescription())
            .applyRules(dto.getApplyRules())
            .sapId(dto.getSapId())
            .family(new Family(dto.getFamilyId()))
            .taxType(new TaxType(dto.getTaxTypeId()))
            .expression(new Expression(dto.getExpressionId()))
            .build();
    }

    private Tax generateTaxFromUpdateTaxDTO(UpdateTaxDTO updateTaxDTO) {
        Tax tax = Tax.builder()
            .name(updateTaxDTO.getName())
            .description(updateTaxDTO.getDescription())
            .value(updateTaxDTO.getValue())
            .status(updateTaxDTO.getStatus())
            .applyRules(updateTaxDTO.getApplyRules())
            .sapId(updateTaxDTO.getSapId())
            .build();

        if (updateTaxDTO.getFamilyId() != null) {
            tax.setFamily(new Family(updateTaxDTO.getFamilyId()));
        }

        if (updateTaxDTO.getTaxTypeId() != null) {
            tax.setTaxType(new TaxType(updateTaxDTO.getTaxTypeId()));
        }

        if (updateTaxDTO.getExpressionId() != null) {
            tax.setExpression(new Expression(updateTaxDTO.getExpressionId()));
        }

        return tax;
    }

}
