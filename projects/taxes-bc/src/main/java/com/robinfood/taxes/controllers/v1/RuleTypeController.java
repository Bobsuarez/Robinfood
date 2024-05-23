package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.LIST_RULE_TYPES;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.controllers.v1.docs.RuleTypeControllerDocs;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.model.RuleTypeDTO;
import com.robinfood.taxes.models.RuleType;
import com.robinfood.taxes.services.RuleTypeService;
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
@RequestMapping(value = "v1/rule-types")
public class RuleTypeController implements RuleTypeControllerDocs {

    private final RuleTypeService ruleTypeService;

    private final ModelMapper modelMapper;

    public RuleTypeController(RuleTypeService ruleTypeService, ModelMapper modelMapper) {
        this.ruleTypeService = ruleTypeService;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Override
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_RULE_TYPES + "')")
    public ResponseEntity<ApiResponseDTO<List<RuleTypeDTO>>> findAll() {

        log.trace("Obtaining rule types in service.");
        List<RuleType> ruleTypeList = ruleTypeService.findAll();
        log.trace("Rule types obtained successfully of service {}.", ruleTypeList);

        log.trace("Generating DTO from rule types List");
        List<RuleTypeDTO> ruleTypeDTOList = ruleTypeList.stream()
            .map(ruleType -> modelMapper.map(ruleType, RuleTypeDTO.class))
            .collect(Collectors.toList());
        log.trace("DTO list generated successfully. {}", ruleTypeDTOList);

        log.trace("Creating ApiResponse from list rule types");
        return ResponseEntity.ok(ApiResponseDTO.<List<RuleTypeDTO>>builder()
            .code(HttpStatus.OK.value())
            .data(ruleTypeDTOList)
            .build());
    }

}
