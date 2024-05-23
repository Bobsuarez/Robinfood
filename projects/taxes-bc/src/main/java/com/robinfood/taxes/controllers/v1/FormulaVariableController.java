package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.CREATE_FORMULA_VARIABLES;
import static com.robinfood.taxes.constants.PermissionsConstants.DELETE_FORMULA_VARIABLES;
import static com.robinfood.taxes.constants.PermissionsConstants.LIST_FORMULA_VARIABLES;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.controllers.v1.docs.FormulaVariableControllerDocs;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.create.CreateFormulaVariableDTO;
import com.robinfood.taxes.dto.v1.model.FormulaVariableDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.services.FormulaVariableService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/formula-variables")
public class FormulaVariableController implements FormulaVariableControllerDocs {

    private final FormulaVariableService formulaVariableService;

    private final ModelMapper modelMapper;

    public FormulaVariableController(FormulaVariableService formulaVariableService,
        ModelMapper modelMapper) {
        this.formulaVariableService = formulaVariableService;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @Override
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_FORMULA_VARIABLES + "')")
    public ResponseEntity<ApiResponseDTO<Page<FormulaVariableDTO>>> findAll(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<FormulaVariable> formulaVariables = formulaVariableService.findAll(pageable);
        log.trace("FormulaVariables obtained from service successfully {}", formulaVariables);

        log.trace("Creating formulaVariables DTO list.");
        Page<FormulaVariableDTO> formulaVariableDTOList = formulaVariables
            .map(formulaVariable -> modelMapper.map(formulaVariable, FormulaVariableDTO.class));
        log.trace("DTO created successfully {}.", formulaVariableDTOList);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<Page<FormulaVariableDTO>>builder()
                .code(HttpStatus.OK.value())
                .data(formulaVariableDTOList)
                .build());

    }

    @BasicLog
    @Override
    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + CREATE_FORMULA_VARIABLES + "')")
    public ResponseEntity<ApiResponseDTO<FormulaVariableDTO>> create(
        @RequestBody @Valid CreateFormulaVariableDTO createFormulaVariableDTO)
        throws BusinessRuleException, JsonProcessingException {

        log.trace("Generating formula variables object from DTO {}", createFormulaVariableDTO);
        FormulaVariable formulaVariable = modelMapper
            .map(createFormulaVariableDTO, FormulaVariable.class);
        log.trace("Formula variables object generate successfully {}", formulaVariable);

        FormulaVariable createdFormulaVariable = formulaVariableService.create(formulaVariable);
        log.trace("Formula variables created successfully on service {}", createdFormulaVariable);

        log.trace("Generating DTO from created formula variables {}", createdFormulaVariable);
        FormulaVariableDTO formulaVariableDTO = modelMapper
            .map(createdFormulaVariable, FormulaVariableDTO.class);
        log.trace("DTO generated successfully {}", formulaVariableDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponseDTO.<FormulaVariableDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Formula variable created successfully")
                .data(formulaVariableDTO)
                .build());

    }

    @BasicLog
    @Override
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + DELETE_FORMULA_VARIABLES + "')")
    public ResponseEntity<ApiResponseDTO<String>> delete(@PathVariable Long id)
        throws BusinessRuleException, JsonProcessingException {
        log.trace("Request received on controller to delete formula variable with id {}.", id);

        formulaVariableService.delete(id);

        log.trace("Generating ApiResponse from delete family");
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Formula variable deleted successfully.")
                .build());
    }

}
