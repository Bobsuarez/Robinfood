package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.GeneralConstants.ACTIVE_STATUS;
import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_TAX_RULES;
import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_TAX_RULES;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_TAX_RULES;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static com.robinfood.taxes.constants.PermissionsConstants.UPDATE_TAX_RULES;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.controllers.v1.docs.TaxRuleControllerDocs;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.TaxRuleCompleteDTO;
import com.robinfood.taxes.dto.v1.create.CreateTaxRuleDTO;
import com.robinfood.taxes.dto.v1.model.TaxRuleDTO;
import com.robinfood.taxes.dto.v1.update.UpdateTaxRuleDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.RuleType;
import com.robinfood.taxes.models.RuleVariable;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxRule;
import com.robinfood.taxes.services.TaxRuleService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(value = "v1/tax-rules")
public class TaxRuleController implements TaxRuleControllerDocs {

    private final TaxRuleService taxRuleService;

    private final ModelMapper modelMapper;

    public TaxRuleController(@NotNull final TaxRuleService taxRuleService,
        @NotNull final ModelMapper modelMapper) {
        this.taxRuleService = taxRuleService;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @Override
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_TAX_RULES + "')")
    public ResponseEntity<ApiResponseDTO<Page<TaxRuleCompleteDTO>>> list(
        @RequestParam(value = "tax_id", required = false) @Valid Long taxId,
        @RequestParam(value = "status", required = false) @Valid Integer status,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<TaxRule> taxRules = taxRuleService.list(taxId, status, pageable);
        log.trace("TaxRules obtained from service successfully {}", taxRules);

        log.trace("Creating taxRules DTO list.");
        Page<TaxRuleCompleteDTO> taxRuleDTOS = taxRules
            .map(taxRule -> modelMapper.map(taxRule, TaxRuleCompleteDTO.class));
        log.trace("DTO created successfully {}.", taxRuleDTOS);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<Page<TaxRuleCompleteDTO>>builder()
                .code(HttpStatus.OK.value())
                .data(taxRuleDTOS)
                .build());

    }

    @BasicLog
    @Override
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + CREATE_TAX_RULES + "')")
    public ResponseEntity<ApiResponseDTO<TaxRuleDTO>> create(
        @RequestBody @Valid CreateTaxRuleDTO createTaxRule)
        throws BusinessRuleException, JsonProcessingException {
        log.trace("Transforming received DTO to entity object: {}.", createTaxRule);
        TaxRule taxRule = generateTaxRuleFromCreateTaxRuleDTO(createTaxRule);
        log.trace("Entity object created successfully: {}.", taxRule);

        TaxRule createdTaxRule = taxRuleService.create(taxRule);

        log.trace("Generating TaxRuleDTO from created taxRule: {}.", createdTaxRule);
        TaxRuleDTO taxRuleMapped = generateTaxRuleDTOFromTaxRule(createdTaxRule);
        log.trace("TaxRuleDTO generated successfully: {}.", taxRuleMapped);

        log.trace("Creating ApiResponse object from created taxRule: {}.", taxRuleMapped);
        ResponseEntity<ApiResponseDTO<TaxRuleDTO>>
            response = ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<TaxRuleDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Tax rule created successfully.")
                .data(taxRuleMapped)
                .build());
        log.trace("ApiResponse object created successfully: {}.", response);

        return response;
    }

    @BasicLog
    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + UPDATE_TAX_RULES + "')")
    public ResponseEntity<ApiResponseDTO<TaxRuleDTO>> update(@PathVariable Long id,
        @RequestBody @Valid UpdateTaxRuleDTO updateTaxRuleDTO)
        throws BusinessRuleException, JsonProcessingException {
        log.trace("Transforming received updateTaxRuleDTO to entity object: {}.", updateTaxRuleDTO);
        TaxRule taxRule = generateTaxRuleFromUpdateTaxRuleDTO(updateTaxRuleDTO);
        log.trace("Entity object to update created successfully: {}.", taxRule);

        TaxRule updatedTaxRule = taxRuleService.update(id, taxRule);
        log.trace("Tax rule updated successfully on service");

        log.trace("Generating TaxRuleDTO from updated tax rule: {}.", updatedTaxRule);
        TaxRuleDTO updatedTaxRuleDTO = generateTaxRuleDTOFromTaxRule(updatedTaxRule);
        log.trace("TaxRuleDTO generated successfully from updated tax rule: {}.",
            updatedTaxRuleDTO);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<TaxRuleDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Tax rule updated successfully.")
                .data(updatedTaxRuleDTO)
                .build());
    }

    @BasicLog
    @Override
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + DELETE_TAX_RULES + "')")
    public ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable Long id)
        throws BusinessRuleException, JsonProcessingException {
        log.trace("Request received on delete TAX RULE controller with id {}", id);

        taxRuleService.delete(id);
        log.trace("Tax rule deleted successfully on service");

        log.trace("Generating ApiResponse from delete tax rule");
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Tax rule deleted successfully")
                .build());
    }

    private static TaxRule generateTaxRuleFromCreateTaxRuleDTO(CreateTaxRuleDTO createTaxRule) {
        return TaxRule.builder()
            .tax(new Tax(createTaxRule.getTaxId()))
            .leftVariable(new RuleVariable(createTaxRule.getLeftVariableId()))
            .rightVariable(new RuleVariable(createTaxRule.getRightVariableId()))
            .ruleType(new RuleType(createTaxRule.getRuleTypeId()))
            .status(ACTIVE_STATUS)
            .build();
    }

    private static TaxRule generateTaxRuleFromUpdateTaxRuleDTO(UpdateTaxRuleDTO updateTaxRuleDTO) {
        TaxRule taxRule = TaxRule.builder()
            .status(updateTaxRuleDTO.getStatus())
            .build();

        if (updateTaxRuleDTO.getTaxId() != null) {
            taxRule.setTax(new Tax(updateTaxRuleDTO.getTaxId()));
        }

        if (updateTaxRuleDTO.getLeftVariableId() != null) {
            taxRule.setLeftVariable(new RuleVariable(updateTaxRuleDTO.getLeftVariableId()));
        }

        if (updateTaxRuleDTO.getRightVariableId() != null) {
            taxRule.setRightVariable(new RuleVariable(updateTaxRuleDTO.getRightVariableId()));
        }

        if (updateTaxRuleDTO.getRuleTypeId() != null) {
            taxRule.setRuleType(new RuleType(updateTaxRuleDTO.getRuleTypeId()));
        }

        return taxRule;
    }

    private TaxRuleDTO generateTaxRuleDTOFromTaxRule(TaxRule taxRule) {
        TaxRuleDTO taxRuleDTO = modelMapper.map(taxRule, TaxRuleDTO.class);
        taxRuleDTO.setTaxId(taxRule.getTax().getId());
        taxRuleDTO.setLeftVariableId(taxRule.getLeftVariable().getId());
        taxRuleDTO.setRightVariableId(taxRule.getRightVariable().getId());
        taxRuleDTO.setRuleTypeId(taxRule.getRuleType().getId());
        return taxRuleDTO;
    }
}
