package com.robinfood.configurations.controllers.v1;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.controllers.v1.docs.CompanyCountryControllerDocs;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.models.CompanyCountryDTO;
import com.robinfood.configurations.services.BrandService;
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
@RequestMapping(value = "/v1/company-countries")
public class CompanyCountryController implements CompanyCountryControllerDocs {

    private final BrandService service;

    public CompanyCountryController(BrandService service) {
        this.service = service;
    }

    /**
     * Método que consulta la marcas por compañia
     *
     * @return respuesta JSON con objeto de marca
     */
    @Override
    @BasicLog
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{companyCountryId}/brands/{branId}"
    )
    @PreAuthorize("hasRole('" + PUBLIC + "') || hasRole('" + SERVICE + "') ")
    public ResponseEntity<ApiResponseDTO<CompanyCountryDTO>> findBrandByCompany(Long companyCountryId, Long branId) {
        log.info("Getting Brand by companyCountryId = ({}) and brandId = ({})", companyCountryId, branId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.<CompanyCountryDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message("Brand retrieved successfully")
                        .data(
                                CompanyCountryDTO.fromBrandCompanyChannel(
                                        service.getByBrandIdAndCompanyId(branId, companyCountryId))
                        )
                        .build());
    }
}
