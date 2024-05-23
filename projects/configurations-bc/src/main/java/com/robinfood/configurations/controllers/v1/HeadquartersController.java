package com.robinfood.configurations.controllers.v1;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.HeadquartersControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.CompanyHeadquarterDTO;
import com.robinfood.configurations.services.HeadquartersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;

@Slf4j
@RestController
@RequestMapping(value = "/v1/companies/company-countries/{companyCountryId}/headquarters")
public class HeadquartersController implements HeadquartersControllerDocs {

    private final HeadquartersService service;

    public HeadquartersController(HeadquartersService service) {
        this.service = service;
    }

    @Override
    @BasicLog
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<CompanyHeadquarterDTO>> findByCompanyCountryId(Long companyId) {
        log.info("Getting Company Headquarter by companyCountryId = ({})", companyId);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<CompanyHeadquarterDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Company Headquarter retrieved successfully")
                .data(CompanyHeadquarterDTO.fromHeadquarter(service.getByCompanyCountryId(companyId)))
                .build());

    }
}
