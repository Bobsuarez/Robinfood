package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.LIST_RULE_VARIABLES;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.constants.PermissionsConstants;
import com.robinfood.taxes.controllers.v1.docs.RuleVariableControllerDocs;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateRuleVariableDTO;
import com.robinfood.taxes.dto.v1.model.RuleVariableDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.RuleVariable;
import com.robinfood.taxes.services.RuleVariableService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/rule-variables")
public class RuleVariableController implements RuleVariableControllerDocs {

    private final RuleVariableService ruleVariableService;

    private final ModelMapper modelMapper;

    public RuleVariableController(
        RuleVariableService ruleVariableService, ModelMapper modelMapper) {
        this.ruleVariableService = ruleVariableService;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_RULE_VARIABLES + "')")
    public ResponseEntity<ApiResponseDTO<List<RuleVariableDTO>>> findAll() {

        log.trace("Obtaining rule variables in service.");
        List<RuleVariable> ruleVariableList = ruleVariableService.findAll();
        log.trace("Rule variables obtained successfully of service {}.", ruleVariableList);

        log.trace("Generating DTO from ruleVariableList");
        List<RuleVariableDTO> ruleVariableDTOList = ruleVariableList.stream()
            .map(ruleVariable -> modelMapper.map(ruleVariable, RuleVariableDTO.class))
            .collect(Collectors.toList());
        log.trace("DTO list generated successfully. {}", ruleVariableDTOList);

        log.trace("Creating ApiResponse from list rule variables");
        return ResponseEntity.ok(ApiResponseDTO.<List<RuleVariableDTO>>builder()
            .code(HttpStatus.OK.value())
            .data(ruleVariableDTOList)
            .build());
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('" + PermissionsConstants.SERVICE + "') || hasRole('"
        + PermissionsConstants.CREATE_RULE_VARIABLES + "')")
    public ResponseEntity<ApiResponseDTO<RuleVariableDTO>> create(
        @RequestBody @Valid CreateRuleVariableDTO createRuleVariableDTO)
        throws BusinessRuleException, JsonProcessingException {
        log.trace("Transforming received DTO to entity object: {}.", createRuleVariableDTO);
        RuleVariable ruleVariable = generateRuleVariableFromCreateRuleVariableDTO(createRuleVariableDTO);
        log.trace("Entity object created successfully: {}.", ruleVariable);

        RuleVariable ruleVariableCreated = ruleVariableService.create(ruleVariable);

        log.trace("Generating rule variable DTO from created rule family: {}.", ruleVariableCreated);
        RuleVariableDTO ruleVariableMapped = modelMapper.map(ruleVariable, RuleVariableDTO.class);
        log.trace("Rule variable DTO generated successfully: {}.", ruleVariableMapped);

        log.trace("Creating ApiResponse object from created rule variable: {}.", ruleVariableMapped);
        ResponseEntity<ApiResponseDTO<RuleVariableDTO>>
            response = ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<RuleVariableDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Rule variable created successfully.")
                .data(ruleVariableMapped)
                .build());
        log.trace("ApiResponse object created successfully: {}.", response);

        return response;
    }


    @BasicLog
    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('" + PermissionsConstants.SERVICE + "') || hasRole('"
        + PermissionsConstants.DELETE_RULE_VARIABLES + "')")
    public ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable Long id)
        throws BusinessRuleException, JsonProcessingException {

        log.trace("Request received on delete rule variable controller with id {}", id);
        ruleVariableService.delete(id);
        log.trace("Rule Variable deleted on service successfully");

        log.trace("Creating response for delete Rule Variable");
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Rule variable deleted successfully.")
                .build());
    }

    private RuleVariable generateRuleVariableFromCreateRuleVariableDTO(
        CreateRuleVariableDTO createRuleVariableDTO) {

        return RuleVariable.builder()
            .name(createRuleVariableDTO.getName())
            .description(createRuleVariableDTO.getDescription())
            .type(createRuleVariableDTO.getType())
            .path(createRuleVariableDTO.getPath())
            .value(createRuleVariableDTO.getValue())
            .build();
    }

}
