package com.robinfood.configurations.controllers.v1;

import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.CompanyBrandControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.CompanyBrandResponseDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.services.CompanyBrandService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/company-brands")
public class CompanyBrandController implements CompanyBrandControllerDocs {

    @Value("${url.base.logo}")
    private String logoBaseUrl;

    private final CompanyBrandService companyBrandService;

    private final ModelMapper modelMapper;


    public CompanyBrandController(CompanyBrandService companyBrandService,
        ModelMapper modelMapper) {
        this.companyBrandService = companyBrandService;
        this.modelMapper = modelMapper;
    }

    @Override
    @BasicLog
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<CompanyBrandResponseDTO>> findById(Long id) {

        CompanyBrand companyBrand = companyBrandService.findById(id);

        CompanyBrandResponseDTO companyBrandResponseDTO = modelMapper.map(
            companyBrand, CompanyBrandResponseDTO.class
        );

        companyBrandResponseDTO.setLogo(logoBaseUrl.concat(companyBrandResponseDTO.getLogo()));

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<CompanyBrandResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Company brand retrieved successfully")
                .data(companyBrandResponseDTO)
                .build());
    }

    @Override
    @BasicLog
    @GetMapping(value = "menu-brand/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<CompanyBrandResponseDTO>> findByMenuBrandId(
        Long menuBrandId) throws JsonProcessingException, BusinessRuleException {

        CompanyBrand companyBrand = companyBrandService.getByCompanyIdAndMenuBrandId(
            menuBrandId);

        if (companyBrand == null) {
            throw new BusinessRuleException(HttpStatus.NOT_FOUND, "Menu brand doesn't exists.");
        }

        CompanyBrandResponseDTO companyBrandResponseDTO = modelMapper.map(
            companyBrand, CompanyBrandResponseDTO.class
        );

        companyBrandResponseDTO.setLogo(logoBaseUrl.concat(companyBrandResponseDTO.getLogo()));

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<CompanyBrandResponseDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Company brand retrieved successfully")
                .data(companyBrandResponseDTO)
                .build());
    }

}
